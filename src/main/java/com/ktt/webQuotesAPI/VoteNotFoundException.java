package com.ktt.webQuotesAPI;

class VoteNotFoundException extends RuntimeException {

    VoteNotFoundException(Long id) {
        super("Could not find vote " + id);
    }
}
