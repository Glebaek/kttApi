package com.ktt.webQuotesAPI;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class Vote {

    private @Id @GeneratedValue Long id;
    private boolean voteValue;
    private Long votedQuoteID;
    private Long votedByAccID;
    private Date votedOnTime;

    Vote() {}

    Vote(boolean voteValue, Long votedQuoteID, Long votedByAccID) {

        this.voteValue = voteValue;
        this.votedQuoteID = votedQuoteID;
        this.votedByAccID = votedByAccID;
        this.votedOnTime = new java.sql.Date(System.currentTimeMillis());
    }

    public Long getId() {
        return this.id;
    }

    public boolean getVoteValue() {
        return this.voteValue;
    }

    public Long getVotedQuoteID() {
        return this.votedQuoteID;
    }

    public Long getVotedByAccID() {
        return this.votedByAccID;
    }

    public Date getVotedOnTime() {
        return this.votedOnTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVoteValue(boolean voteValue) {
        this.voteValue = voteValue;
    }

    public void setVotedQuoteID(Long votedQuoteID) {
        this.votedQuoteID = votedQuoteID;
    }

    public void setVotedByAccID(Long votedByAccID) {
        this.votedByAccID = votedByAccID;
    }

    public void setVotedOnTime() {
        this.votedOnTime = new java.sql.Date(System.currentTimeMillis());
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Vote))
            return false;
        Vote vote = (Vote) o;
        return Objects.equals(this.id, vote.id) && Objects.equals(this.voteValue, vote.voteValue)
                && Objects.equals(this.votedQuoteID, vote.votedQuoteID) && Objects.equals(this.votedByAccID, vote.votedByAccID)
                && Objects.equals(this.votedOnTime, vote.votedOnTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.voteValue, this.votedQuoteID, this.votedByAccID, this.votedOnTime);
    }

    @Override
    public String toString() {
        return "Quote{" + "id=" + this.id + ", voteValue='" + this.voteValue + '\'' + ", votedQuoteID='" + this.votedQuoteID + '\'' + ", votedByAccID='" + this.votedByAccID + '\'' + ", votedOnTime='" + this.votedOnTime + '\'' + '}';
    }
}
