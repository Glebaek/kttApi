package com.ktt.webQuotesAPI;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
class QuoteController {

    private final QuoteRepository repository;
    private final QuoteModelAssembler assembler;

    QuoteController(QuoteRepository repository, QuoteModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    //get all quotes of one account by its login
    @GetMapping("/quotes/login/{createdByLogin}")
    public CollectionModel<EntityModel<Quote>> all(@PathVariable String createdByLogin) {

        List<EntityModel<Quote>> quotes = repository.findByCreatedByLogin(createdByLogin).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(quotes, linkTo(methodOn(QuoteController.class).all()).withSelfRel());
    }


    // get a random quote
    @GetMapping("/quotes/random")
    EntityModel<Quote> one() {

        Long random = 1L;
        while(repository.existsById(random)){
            random++;
        }

        Random rand = new Random();
        random = rand.nextLong(random - 1L) + 1L;
        Long finalRandom = random;
        Quote quote = repository.findById(random) //
                .orElseThrow(() -> new QuoteNotFoundException(finalRandom));

        return assembler.toModel(quote);
    }


    //get all quotes
    @GetMapping("/quotes")
    CollectionModel<EntityModel<Quote>> all() {

        List<EntityModel<Quote>> quotes = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(quotes, linkTo(methodOn(QuoteController.class).all()).withSelfRel());
    }


    @PostMapping("/quotes")
    ResponseEntity<?> newQuote(@RequestBody Quote newQuote) {

        newQuote.setCreatedOnTime();
        EntityModel<Quote> entityModel = assembler.toModel(repository.save(newQuote));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }


    // get a single quote by ID
    @GetMapping("/quotes/{id}")
    EntityModel<Quote> one(@PathVariable Long id) {

        Quote quote = repository.findById(id) //
                .orElseThrow(() -> new QuoteNotFoundException(id));

        return assembler.toModel(quote);
    }


    @PutMapping("/quotes/{id}")
    ResponseEntity<?> replaceQuote(@RequestBody Quote newQuote, @PathVariable Long id) {

        Quote updatedQuote = repository.findById(id) //
                .map(quote -> {
                    quote.setTextQuote(newQuote.getTextQuote());
                    quote.setCreatedByLogin(newQuote.getCreatedByLogin());
                    quote.setCreatedOnTime();

                    return repository.save(quote);
                }) //
                .orElseGet(() -> {
                    newQuote.setId(id);
                    return repository.save(newQuote);
                });

        EntityModel<Quote> entityModel = assembler.toModel(updatedQuote);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }


    @DeleteMapping("/quotes/{id}")
    ResponseEntity<?> deleteQuote(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
