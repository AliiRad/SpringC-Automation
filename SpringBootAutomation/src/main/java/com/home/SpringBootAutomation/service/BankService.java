package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.model.Bank;

import java.util.List;

public interface BankService {
    Bank save(Bank bank);

    Bank edit(Bank bank);

    Bank remove(Bank bank);

    Bank logicalRemove(Long id);

    Bank findById(Long id);

    List<Bank> findAll();
}
