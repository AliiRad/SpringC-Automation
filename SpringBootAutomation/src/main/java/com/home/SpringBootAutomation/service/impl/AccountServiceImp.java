package com.home.SpringBootAutomation.service.impl;

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
        repository.save(account);
        return account;
    }

    @Override
    public Account edit(Account account) {
        repository.save(account);
        return account;
    }

    @Override
    public Account remove(Account account) {
        if (findById(account.getId()) != null) {
            repository.delete(account);
            return account;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Account logicalRemove(Long id) {
        Account account = findById(id);
        if (account != null) {
            repository.save(account);
            return account;
        } else{
            return null;
        }
    }

    @Override
    public Account findById(Long id) {
       Optional<Account> account= repository.findById(id);
       return (account.orElse(null));
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }
}
