package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.model.Salary;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SalaryService {

    void save(Salary salary) throws Exception;

    void update(Salary salary) throws Exception;

    @Transactional
    void logicalRemove(Long id) throws Exception;

    List<Salary> findAll() throws Exception;

    Salary findById(Long id) throws Exception;

    List<Salary> findSalariesByDeletedFalse() throws Exception;

    Optional<Salary> findSalaryByIdAndDeletedFalse(Long id) throws Exception;

    Optional<Salary> findSalaryByYearAndDeletedFalse(Integer year) throws Exception;
}
