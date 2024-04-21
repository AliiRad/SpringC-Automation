package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.model.Account;

import java.util.List;

public interface AccountService {
    Account save(Account account);

    Account edit(Account account);

    Account remove(Account account);

    Account logicalRemove(Long id);

    Account findById(Long id);

    List<Account> findAll();
}
