package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    @Modifying
    @Query("update accountEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    List<Account> findAccountByDeletedFalse();

    Optional<Account> findAccountByIdAndDeletedFalse(Long id);

    Optional<Account> findAccountByAccountNumberAndDeletedFalse(String accountNumber);

    Long countByDeletedFalse();
}