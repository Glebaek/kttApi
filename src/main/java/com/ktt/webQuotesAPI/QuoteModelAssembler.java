package com.ktt.webQuotesAPI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class QuoteModelAssembler implements RepresentationModelAssembler<Quote, EntityModel<Quote>> {

    @Override
    public EntityModel<Quote> toModel(Quote quote) {

        return EntityModel.of(quote, //
                linkTo(methodOn(QuoteController.class).one(quote.getId())).withSelfRel(),
                linkTo(methodOn(QuoteController.class).all()).withRel("quotes"));
    }
}
