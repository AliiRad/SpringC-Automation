package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.BankTransaction;
import com.home.SpringBootAutomation.repository.BankTransactionRepository;
import com.home.SpringBootAutomation.service.BankTransactionService;
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
        return repository.save(bankTransaction);
    }

    @Override
    public BankTransaction edit(BankTransaction bankTransaction) throws NoContentException {
        repository.findBankTransactionByIdAndDeletedFalse(bankTransaction.getId()).orElseThrow(
                () -> new NoContentException("No BankTransaction Found with id : " + bankTransaction.getId())
        );
        return repository.save(bankTransaction);
    }

    @Override
    public void logicalRemove(Long id) throws NoContentException {
    }

    public BankTransaction remove(Long id) throws NoContentException {
        BankTransaction bankTransaction = repository.findById(id).orElseThrow(
                () -> new NoContentException("No Bank Transaction Found with id : " + id)
        );
        repository.deleteById(id);
        return bankTransaction;
    }

    @Override
    public List<BankTransaction> findAll() {
        return repository.findAll();
    }

    @Override
    public BankTransaction findById(Long id) throws NoContentException {
        return repository.findById(id).orElseThrow(
                () -> new NoContentException("No BankT ransaction Found with id : " + id)
        );
    }

    @Override
    public Long getBankTransactionsCount() {
        return null;
    }

    @Override
    public BankTransaction logicalRemoveWithReturn(Long id) throws NoContentException {
        return null;
    }

    @Override
    public List<BankTransaction> findBankTransactionByDeletedFalse() {
        return null;
    }

    @Override
    public Optional<BankTransaction> findBankTransactionByIdAndDeletedFalse(Long id) {
        return Optional.empty();
    }

    @Override
    public Long countByDeletedFalse() {
        return null;
    }
}