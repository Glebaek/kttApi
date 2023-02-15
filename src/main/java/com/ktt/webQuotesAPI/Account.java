package com.ktt.webQuotesAPI;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class Account {

    private @Id @GeneratedValue Long id;
    private String login;
    private String password;

    Account() {}

    Account(String login, String password) {

        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Account))
            return false;
        Account account = (Account) o;
        return Objects.equals(this.id, account.id) && Objects.equals(this.login, account.login)
                 && Objects.equals(this.password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.login, this.password);
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + this.id + ", login='" + this.login + '\'' + ", password='" + this.password + '\'' + '}';
    }
}
