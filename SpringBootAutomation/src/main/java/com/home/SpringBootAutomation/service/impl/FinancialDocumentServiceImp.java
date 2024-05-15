package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.FinancialDocument;
import com.home.SpringBootAutomation.repository.FinancialDocumentRepository;
import com.home.SpringBootAutomation.service.FinancialDocumentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialDocumentServiceImp implements FinancialDocumentService {
    private final FinancialDocumentRepository repository;

    public FinancialDocumentServiceImp(FinancialDocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public FinancialDocument save(FinancialDocument financialDocument) {
        return repository.save(financialDocument);
    }

    @Override
    public FinancialDocument edit(FinancialDocument financialDocument) throws NoContentException {
        repository.findFinancialDocumentByIdAndDeletedFalse(financialDocument.getId()).orElseThrow(
                () -> new NoContentException("No FinancialDocument Found with id : " + financialDocument.getId())
        );
        return repository.save(financialDocument);
    }

    @Override
    public void logicalRemove(Long id) throws NoContentException {
    }

    public FinancialDocument remove(Long id) throws NoContentException {
        FinancialDocument financialDocument = repository.findById(id).orElseThrow(
                () -> new NoContentException("No Financial Document Found with id : " + id)
        );
        repository.deleteById(id);
        return financialDocument;
    }

    @Override
    public List<FinancialDocument> findAll() {
        return repository.findAll();
    }

    @Override
    public FinancialDocument findById(Long id) throws NoContentException {
        return repository.findById(id).orElseThrow(
                () -> new NoContentException("No Financial Document Found with id : " + id)
        );
    }

    @Override
    public Long getFinancialDocumentsCount() {
        return null;
    }

    @Override
    public FinancialDocument logicalRemoveWithReturn(Long id) throws NoContentException {
        return null;
    }

    @Override
    public List<FinancialDocument> findFinancialDocumentByDeletedFalse() {
        return null;
    }

    @Override
    public Optional<FinancialDocument> findFinancialDocumentByIdAndDeletedFalse(Long id) {
        return Optional.empty();
    }

    @Override
    public Long countByDeletedFalse() {
        return null;
    }
}