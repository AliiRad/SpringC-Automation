package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Account;
import com.home.SpringBootAutomation.repository.AccountRepository;
import com.home.SpringBootAutomation.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImp implements AccountService {
    private final AccountRepository repository;

    public AccountServiceImp(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account save(Account account) {
        return repository.save(account);
    }

    @Override
    public Account edit(Account account) throws NoContentException {
        repository.findAccountByIdAndDeletedFalse(account.getId()).orElseThrow(
                () -> new NoContentException("No Account Found with id : " + account.getId())
        );
        return repository.save(account);
    }

    @Override
    public void logicalRemove(Long id) throws NoContentException {
    }

    public Account remove(Long id) throws NoContentException {
        Account account = repository.findById(id).orElseThrow(
                () -> new NoContentException("No Account Found with id : " + id)
        );
        repository.deleteById(id);
        return account;
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public Account findById(Long id) throws NoContentException {
        return repository.findById(id).orElseThrow(
                () -> new NoContentException("No Account Found with id : " + id)
        );
    }

    @Override
    public Long getAccountsCount() {
        return null;
    }

    @Override
    public Account logicalRemoveWithReturn(Long id) throws NoContentException {
        return null;
    }

    @Override
    public List<Account> findAccountByDeletedFalse() {
        return null;
    }

    @Override
    public Optional<Account> findAccountByIdAndDeletedFalse(Long id) {
        return Optional.empty();
    }

    @Override
    public Long countByDeletedFalse() {
        return null;
    }
}