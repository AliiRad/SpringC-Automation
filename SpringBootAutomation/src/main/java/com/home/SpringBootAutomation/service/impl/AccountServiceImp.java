package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Account;
import com.home.SpringBootAutomation.repository.AccountRepository;
import com.home.SpringBootAutomation.service.AccountService;
import jakarta.transaction.Transactional;
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

    public Account update(Account account) throws NoContentException {
        Optional<Account> optionalAccount = repository.findAccountByIdAndDeletedFalse(account.getId());

        if (optionalAccount.isPresent()) {
            return repository.save(account);
        } else {
            throw new NoContentException("Account not found!");
        }
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException{
        Optional<Account> optionalAccount = repository.findAccountByIdAndDeletedFalse(id);
        if (optionalAccount.isPresent()) {
            repository.logicalRemove(id);
        } else {
            throw new NoContentException("Account not found!");
        }
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) throws NoContentException {
        Optional<Account> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("Account not found !");
        }
    }

    @Override
    public Long getAccountsCount() {
        return repository.count();
    }

    @Override
    public Account logicalRemoveWithReturn(Long id) throws NoContentException {
        Optional<Account> optionalAccount = repository.findAccountByIdAndDeletedFalse(id);

        if (optionalAccount.isPresent()) {
            Account oldAccount = optionalAccount.get();
            oldAccount.setDeleted(true);
            return repository.save(oldAccount);
        } else {
            throw new NoContentException("Account not found !");
        }
    }

    @Override
    public List<Account> findAccountByDeletedFalse() {
        return repository.findAccountByDeletedFalse();
        }

    @Override
    public Optional<Account> findAccountByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Account> optional = repository.findAccountByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("Account not found!");
        }
    }

    @Override
    public Long countByDeletedFalse() {
        return repository.countByDeletedFalse();
    }
}