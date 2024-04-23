package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.model.FinancialDocument;

import java.util.List;

public interface FinancialDocumentService {
    FinancialDocument save(FinancialDocument financialDocument);

    FinancialDocument edit(FinancialDocument financialDocument);

    FinancialDocument remove(FinancialDocument financialDocument);

    FinancialDocument logicalRemove(Long id);

    FinancialDocument findById(Long id);

    List<FinancialDocument> findAll();
}
