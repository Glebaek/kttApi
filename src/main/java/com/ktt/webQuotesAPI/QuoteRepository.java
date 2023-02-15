package com.ktt.webQuotesAPI;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface QuoteRepository extends JpaRepository<Quote, Long> {

    List<Quote> findByCreatedByLogin(String createdByLogin);

}