package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.Model.Salary;

import java.util.List;
import java.util.Optional;

public interface SalaryService {
    void save(Salary salary) throws Exception;
    void edit(Salary salary) throws Exception;
    void logicalRemove(Long id) throws Exception;

    List<Salary> findAll() throws Exception;
    List<Salary> findAllByDeletedFalse() throws Exception;

    Optional<Salary> findById(Long id) throws Exception;
    Optional<Salary> findByYear(Integer year) throws Exception;
}
