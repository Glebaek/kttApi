package com.ktt.webQuotesAPI;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
class AccountController {

    private final AccountRepository repository;
    private final AccountModelAssembler assembler;

    AccountController(AccountRepository repository, AccountModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    // get all accounts
    @GetMapping("/accounts")
    CollectionModel<EntityModel<Account>> all() {

        List<EntityModel<Account>> accounts = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(accounts, linkTo(methodOn(AccountController.class).all()).withSelfRel());
    }


    @PostMapping("/accounts")
    ResponseEntity<?> newAccount(@RequestBody Account newAccount) {

        EntityModel<Account> entityModel = assembler.toModel(repository.save(newAccount));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }


    // get a single account by ID
    @GetMapping("/accounts/{id}")
    EntityModel<Account> one(@PathVariable Long id) {

        Account account = repository.findById(id) //
                .orElseThrow(() -> new AccountNotFoundException(id));

        return assembler.toModel(account);
    }


    @PutMapping("/accounts/{id}")
    ResponseEntity<?> replaceAccount(@RequestBody Account newAccount, @PathVariable Long id) {

        Account updatedAccount = repository.findById(id) //
                .map(account -> {
                    account.setLogin(newAccount.getLogin());
                    account.setPassword(newAccount.getPassword());
                    return repository.save(account);
                }) //
                .orElseGet(() -> {
                    newAccount.setId(id);
                    return repository.save(newAccount);
                });

        EntityModel<Account> entityModel = assembler.toModel(updatedAccount);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }


    @DeleteMapping("/accounts/{id}")
    ResponseEntity<?> deleteAccount(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
