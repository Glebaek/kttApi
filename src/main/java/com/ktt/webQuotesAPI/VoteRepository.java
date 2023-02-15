package com.ktt.webQuotesAPI;

import org.springframework.data.jpa.repository.JpaRepository;;

import java.util.List;

interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findByVotedByAccID(Long votedByAccID);
    List<Vote> findByVotedOnTimeAfter(String votedOnTime);
}

