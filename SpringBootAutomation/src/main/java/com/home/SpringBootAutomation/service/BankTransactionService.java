package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.model.BankTransaction;

import java.util.List;

public interface BankTransactionService {
    BankTransaction save(BankTransaction bankTransaction);

    BankTransaction edit(BankTransaction bankTransaction);

    BankTransaction remove(BankTransaction bankTransaction);

    BankTransaction logicalRemove(Long id);

    BankTransaction findById(Long id);

    List<BankTransaction> findAll();
}
