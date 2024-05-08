package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.FinancialDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinancialDocumentRepository extends JpaRepository<FinancialDocument,Long> {
    @Modifying
    @Query("update financialDocumentEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    List<FinancialDocument> findFinancialDocumentByDeletedFalse();

    Optional<FinancialDocument> findFinancialDocumentByIdAndDeletedFalse(Long id);

    Optional<FinancialDocument> findFinancialDocumentByAmountAndDeletedFalse(int amount);

    Long countByDeletedFalse();
}