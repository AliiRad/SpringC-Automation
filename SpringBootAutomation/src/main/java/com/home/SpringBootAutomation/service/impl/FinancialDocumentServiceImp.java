package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.FinancialDocument;
import com.home.SpringBootAutomation.repository.FinancialDocumentRepository;
import com.home.SpringBootAutomation.service.FinancialDocumentService;
import jakarta.transaction.Transactional;
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

    public FinancialDocument update(FinancialDocument financialDocument) throws NoContentException {
        Optional<FinancialDocument> optionalFinancialDocument = repository.findFinancialDocumentByIdAndDeletedFalse(financialDocument.getId());

        if (optionalFinancialDocument.isPresent()) {
            return repository.save(financialDocument);
        } else {
            throw new NoContentException("Financial Document not found!");
        }
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException{
        Optional<FinancialDocument> optionalFinancialDocument = repository.findFinancialDocumentByIdAndDeletedFalse(id);
        if (optionalFinancialDocument.isPresent()) {
            repository.logicalRemove(id);
        } else {
            throw new NoContentException("Financial Document not found!");
        }
    }

    @Override
    public List<FinancialDocument> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<FinancialDocument> findById(Long id) throws NoContentException {
        Optional<FinancialDocument> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("Financial Document not found !");
        }
    }

    @Override
    public Long getFinancialDocumentsCount() {
        return repository.count();
    }

    @Override
    public FinancialDocument logicalRemoveWithReturn(Long id) throws NoContentException {
        Optional<FinancialDocument> optionalFinancialDocument = repository.findFinancialDocumentByIdAndDeletedFalse(id);

        if (optionalFinancialDocument.isPresent()) {
            FinancialDocument oldFinancialDocument = optionalFinancialDocument.get();
            oldFinancialDocument.setDeleted(true);
            return repository.save(oldFinancialDocument);
        } else {
            throw new NoContentException("Financial Document not found !");
        }
    }

    @Override
    public List<FinancialDocument> findFinancialDocumentByDeletedFalse() {
        return repository.findFinancialDocumentByDeletedFalse();
    }

    @Override
    public Optional<FinancialDocument> findFinancialDocumentByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<FinancialDocument> optional = repository.findFinancialDocumentByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("Financial Document not found!");
        }
    }

    @Override
    public Long countByDeletedFalse() {
        return repository.countByDeletedFalse();
    }
}