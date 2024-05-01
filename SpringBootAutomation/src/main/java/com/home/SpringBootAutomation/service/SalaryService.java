package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Salary;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SalaryService {
    void save(Salary salary) throws Exception;
    void update(Salary salary) throws NoContentException;

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Salary> findAll() throws Exception;
    Optional<Salary> findById(Long id) throws NoContentException;
    Long getSalariesCount();

    Salary logicalRemoveWithReturn(Long id) throws NoContentException;

    List<Salary> findSalaryByDeletedFalse() throws Exception;
    Optional<Salary> findSalaryByIdAndDeletedFalse(Long id) throws NoContentException;
    Optional<Salary> findSalaryByYearAndDeletedFalse(Integer year) throws NoContentException;
}
