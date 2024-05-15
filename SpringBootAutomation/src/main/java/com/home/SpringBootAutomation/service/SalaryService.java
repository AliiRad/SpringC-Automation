package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Salary;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SalaryService {
    Salary save(Salary salary);
    Salary update(Salary salary) throws NoContentException;
    Salary remove(Long id) throws NoContentException;

    @Transactional
    Salary logicalRemove(Long id) throws NoContentException;

    List<Salary> findAll();
    Salary findById(Long id) throws NoContentException;
    Long getSalariesCount();

    Salary logicalRemoveWithReturn(Long id) throws NoContentException;

    List<Salary> findSalaryByDeletedFalse();
    Salary findSalaryByIdAndDeletedFalse(Long id) throws NoContentException;
    Salary findSalaryByYearAndDeletedFalse(Integer year) throws NoContentException;
}
