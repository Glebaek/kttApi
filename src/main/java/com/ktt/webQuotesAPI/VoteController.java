package com.ktt.webQuotesAPI;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



@RestController
class VoteController {

    private final VoteRepository repository;
    private final VoteModelAssembler assembler;

    VoteController(VoteRepository repository, VoteModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    //get all votes from one account by its ID
    @GetMapping("/votes/accID/{votedByAccID}")
    public CollectionModel<EntityModel<Vote>> all(@PathVariable Long votedByAccID) {

        List<EntityModel<Vote>> votes = repository.findByVotedByAccID(votedByAccID).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());;


        return CollectionModel.of(votes, linkTo(methodOn(VoteController.class).all()).withSelfRel());
    }


    //get all votes
    @GetMapping("/votes")
    CollectionModel<EntityModel<Vote>> all() {

        List<EntityModel<Vote>> votes = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(votes, linkTo(methodOn(VoteController.class).all()).withSelfRel());
    }


    @PostMapping("/votes")
    ResponseEntity<?> newVote(@RequestBody Vote newVote) {

        newVote.setVotedOnTime();
        EntityModel<Vote> entityModel = assembler.toModel(repository.save(newVote));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }


    // get a single vote by id
    @GetMapping("/votes/{id}")
    EntityModel<Vote> one(@PathVariable Long id) {

        Vote vote = repository.findById(id) //
                .orElseThrow(() -> new VoteNotFoundException(id));

        return assembler.toModel(vote);
    }


    @PutMapping("/votes/{id}")
    ResponseEntity<?> replaceVote(@RequestBody Vote newVote, @PathVariable Long id) {

        Vote updatedVote = repository.findById(id) //
                .map(vote -> {
                    vote.setVoteValue(newVote.getVoteValue());
                    vote.setVotedQuoteID(newVote.getVotedQuoteID());
                    vote.setVotedByAccID(newVote.getVotedByAccID());
                    vote.setVotedOnTime();

                    return repository.save(vote);
                }) //
                .orElseGet(() -> {
                    newVote.setId(id);
                    return repository.save(newVote);
                });

        EntityModel<Vote> entityModel = assembler.toModel(updatedVote);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }


    @DeleteMapping("/votes/{id}")
    ResponseEntity<?> deleteVote(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
