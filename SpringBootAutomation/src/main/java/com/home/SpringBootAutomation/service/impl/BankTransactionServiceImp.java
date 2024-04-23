package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.model.BankTransaction;
import com.home.SpringBootAutomation.repository.BankTransactionRepository;
import com.home.SpringBootAutomation.service.BankTransactionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankTransactionServiceImp implements BankTransactionService {
    private final BankTransactionRepository repository;

    public BankTransactionServiceImp(BankTransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public BankTransaction save(BankTransaction bankTransaction) {
        repository.save(bankTransaction);
        return bankTransaction;
    }

    @Override
    public BankTransaction edit(BankTransaction bankTransaction) {
        repository.save(bankTransaction);
        return bankTransaction;
    }

    @Override
    public BankTransaction remove(BankTransaction bankTransaction) {
        if (findById(bankTransaction.getId()) != null) {
            repository.delete(bankTransaction);
            return bankTransaction;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public BankTransaction logicalRemove(Long id) {
        BankTransaction bankTransaction = findById(id);
        if (bankTransaction != null) {
            repository.save(bankTransaction);
            return bankTransaction;
        } else{
            return null;
        }
    }

    @Override
    public BankTransaction findById(Long id) {
       Optional<BankTransaction> bankTransaction= repository.findById(id);
       return (bankTransaction.orElse(null));
    }

    @Override
    public List<BankTransaction> findAll() {
        return repository.findAll();
    }
}
