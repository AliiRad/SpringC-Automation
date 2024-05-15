package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Account;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account save(Account account);

    Account edit(Account account) throws NoContentException;

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Account> findAll();

    Account findById(Long id) throws NoContentException;

    Long getAccountsCount();

    Account logicalRemoveWithReturn(Long id) throws NoContentException;

    List<Account> findAccountByDeletedFalse();

    Optional<Account> findAccountByIdAndDeletedFalse(Long id) throws NoContentException;

    Long countByDeletedFalse();
}