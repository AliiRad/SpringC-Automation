package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.FinancialDocument;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FinancialDocumentService {
    FinancialDocument save(FinancialDocument financialDocument);

    FinancialDocument edit(FinancialDocument financialDocument) throws NoContentException;

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<FinancialDocument> findAll();

    FinancialDocument findById(Long id) throws NoContentException;

    Long getFinancialDocumentsCount();

    FinancialDocument logicalRemoveWithReturn(Long id) throws NoContentException;

    List<FinancialDocument> findFinancialDocumentByDeletedFalse();

    Optional<FinancialDocument> findFinancialDocumentByIdAndDeletedFalse(Long id) throws NoContentException;

    Long countByDeletedFalse();
}