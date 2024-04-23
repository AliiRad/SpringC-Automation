package com.home.SpringBootAutomation.service.impl;

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
        repository.save(financialDocument);
        return financialDocument;
    }

    @Override
    public FinancialDocument edit(FinancialDocument financialDocument) {
        repository.save(financialDocument);
        return financialDocument;
    }

    @Override
    public FinancialDocument remove(FinancialDocument financialDocument) {
        if (findById(financialDocument.getId()) != null) {
            repository.delete(financialDocument);
            return financialDocument;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public FinancialDocument logicalRemove(Long id) {
        FinancialDocument financialDocument = findById(id);
        if (financialDocument != null) {
            repository.save(financialDocument);
            return financialDocument;
        } else{
            return null;
        }
    }

    @Override
    public FinancialDocument findById(Long id) {
       Optional<FinancialDocument> financialDocument= repository.findById(id);
       return (financialDocument.orElse(null));
    }

    @Override
    public List<FinancialDocument> findAll() {
        return repository.findAll();
    }
}