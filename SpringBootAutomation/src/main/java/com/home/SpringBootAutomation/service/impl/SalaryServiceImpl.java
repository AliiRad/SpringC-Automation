package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.Model.Salary;
import com.home.SpringBootAutomation.repository.SalaryRepository;
import com.home.SpringBootAutomation.service.SalaryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository salaryRepository;

    public SalaryServiceImpl(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @Override
    public void save(Salary salary) throws Exception {
        salaryRepository.save(salary);
    }

    @Override
    public void edit(Salary salary) throws Exception {
        salaryRepository.save(salary);
    }

    @Override
    public void logicalRemove(Long id) throws Exception {
        salaryRepository.logicalRemove(id);
    }

    @Override
    public List<Salary> findAll() throws Exception {
        return salaryRepository.findAll();
    }

    @Override
    public List<Salary> findAllByDeletedFalse() throws Exception {
        return salaryRepository.findAllByDeletedFalse();
    }

    @Override
    public Optional<Salary> findById(Long id) throws Exception {
        return salaryRepository.findById(id);
    }

    @Override
    public Optional<Salary> findByYear(Integer year) throws Exception {
        return salaryRepository.findByYearAndDeletedFalse(year);
    }
}
