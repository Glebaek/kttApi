package com.ktt.webQuotesAPI;


class QuoteNotFoundException extends RuntimeException {

    QuoteNotFoundException(Long id) {
        super("Could not find quote " + id);
    }
}
