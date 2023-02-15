package com.ktt.webQuotesAPI;


import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class Quote {

    private @Id @GeneratedValue Long id;
    private String textQuote;
    private Date createdOnTime;
    private String createdByLogin;

    Quote() {}

    Quote(String textQuote, String createdByLogin) {

        this.textQuote = textQuote;
        this.createdByLogin = createdByLogin;
        this.createdOnTime = new java.sql.Date(System.currentTimeMillis());
    }

    public Long getId() {
        return this.id;
    }

    public String getTextQuote() {
        return this.textQuote;
    }

    public String getCreatedByLogin() {
        return this.createdByLogin;
    }

    public Date getCreatedOnTime() {
        return this.createdOnTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTextQuote(String textQuote) {
        this.textQuote = textQuote;
    }

    public void setCreatedByLogin(String createdByLogin) {
        this.createdByLogin = createdByLogin;
    }

    public void setCreatedOnTime() {
        this.createdOnTime = new java.sql.Date(System.currentTimeMillis());
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Quote))
            return false;
        Quote quote = (Quote) o;
        return Objects.equals(this.id, quote.id) && Objects.equals(this.textQuote, quote.textQuote)
                && Objects.equals(this.createdByLogin, quote.createdByLogin) && Objects.equals(this.createdOnTime, quote.createdOnTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.textQuote, this.createdByLogin, this.createdOnTime);
    }

    @Override
    public String toString() {
        return "Quote{" + "id=" + this.id + ", textQuote='" + this.textQuote + '\'' + ", createdByLogin='" + this.createdByLogin + '\'' + ", createdOnTime='" + this.createdOnTime + '\'' + '}';
    }
}
