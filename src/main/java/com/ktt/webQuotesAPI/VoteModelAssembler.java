package com.ktt.webQuotesAPI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class VoteModelAssembler implements RepresentationModelAssembler<Vote, EntityModel<Vote>> {

    @Override
    public EntityModel<Vote> toModel(Vote vote) {

        return EntityModel.of(vote, //
                linkTo(methodOn(VoteController.class).one(vote.getId())).withSelfRel(),
                linkTo(methodOn(VoteController.class).all()).withRel("votes"));
    }
}
