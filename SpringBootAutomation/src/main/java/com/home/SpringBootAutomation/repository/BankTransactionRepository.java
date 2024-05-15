package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction,Long> {
    @Modifying
    @Query("update bankTransactionEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    List<BankTransaction> findBankTransactionByDeletedFalse();

    Optional<BankTransaction> findBankTransactionByIdAndDeletedFalse(Long id);

    Long countByDeletedFalse();
}
