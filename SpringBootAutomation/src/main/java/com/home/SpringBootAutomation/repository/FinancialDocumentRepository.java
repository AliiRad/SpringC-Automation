package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.FinancialDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialDocumentRepository extends JpaRepository<FinancialDocument,Long> {
}
