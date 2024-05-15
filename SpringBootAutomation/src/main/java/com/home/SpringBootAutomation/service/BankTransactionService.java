package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.BankTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BankTransactionService {
    BankTransaction save(BankTransaction bankTransaction);

    BankTransaction edit(BankTransaction bankTransaction) throws NoContentException;

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<BankTransaction> findAll();

    BankTransaction findById(Long id) throws NoContentException;

    Long getBankTransactionsCount();

    BankTransaction logicalRemoveWithReturn(Long id) throws NoContentException;

    List<BankTransaction> findBankTransactionByDeletedFalse();

    Optional<BankTransaction> findBankTransactionByIdAndDeletedFalse(Long id) throws NoContentException;

    Long countByDeletedFalse();
}