package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
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
        return repository.save(bankTransaction);
    }

    public BankTransaction update(BankTransaction bankTransaction) throws NoContentException {
        Optional<BankTransaction> optionalBankTransaction = repository.findBankTransactionByIdAndDeletedFalse(bankTransaction.getId());

        if (optionalBankTransaction.isPresent()) {
            return repository.save(bankTransaction);
        } else {
            throw new NoContentException("Bank Transaction not found!");
        }
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException{
        Optional<BankTransaction> optionalBankTransaction = repository.findBankTransactionByIdAndDeletedFalse(id);
        if (optionalBankTransaction.isPresent()) {
            repository.logicalRemove(id);
        } else {
            throw new NoContentException("Bank Transaction not found!");
        }
    }

    @Override
    public List<BankTransaction> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<BankTransaction> findById(Long id) throws NoContentException {
        Optional<BankTransaction> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("BankTransaction not found !");
        }
    }

    @Override
    public Long getBankTransactionsCount() {
        return repository.count();
    }

    @Override
    public BankTransaction logicalRemoveWithReturn(Long id) throws NoContentException {
        Optional<BankTransaction> optionalBankTransaction = repository.findBankTransactionByIdAndDeletedFalse(id);

        if (optionalBankTransaction.isPresent()) {
            BankTransaction oldBankTransaction = optionalBankTransaction.get();
            oldBankTransaction.setDeleted(true);
            return repository.save(oldBankTransaction);
        } else {
            throw new NoContentException("Bank Transaction not found!");
        }
    }

    @Override
    public List<BankTransaction> findBankTransactionByDeletedFalse() {
        return repository.findBankTransactionByDeletedFalse();
    }

    @Override
    public Optional<BankTransaction> findBankTransactionByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<BankTransaction> optional = repository.findBankTransactionByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("Bank Transaction not found!");
        }
    }

    @Override
    public Long countByDeletedFalse() {
        return repository.countByDeletedFalse();
    }
}
