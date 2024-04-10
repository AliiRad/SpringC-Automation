package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.Model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank,Long> {
}
