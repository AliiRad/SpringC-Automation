package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Salary;
import com.home.SpringBootAutomation.repository.SalaryRepository;
import com.home.SpringBootAutomation.service.SalaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository repository;

    public SalaryServiceImpl(SalaryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Salary salary) throws Exception {
        repository.save(salary);
    }

    @Override
    public void update(Salary salary) throws NoContentException {
        Optional<Salary> optionalSalary = repository.findSalaryByIdAndDeletedFalse(salary.getId());

        if (optionalSalary.isPresent()) {
            repository.save(salary);
        } else {
            throw new NoContentException("Salary not found !");
        }
    }

    @Transactional
    @Override
    public void logicalRemove(Long id) throws NoContentException {
        Optional<Salary> optionalSalary = repository.findSalaryByIdAndDeletedFalse(id);
        if (optionalSalary.isPresent()) {
            repository.logicalRemove(id);
        } else {
            throw new NoContentException("Salary not found !");
        }
    }

    @Override
    public List<Salary> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Optional<Salary> findById(Long id) throws NoContentException {
        Optional<Salary> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("Salary not found !");
        }
    }

    @Override
    public Long getSalariesCount() {
        return repository.count();
    }

    @Override
    public Salary logicalRemoveWithReturn(Long id) throws NoContentException {
        Optional<Salary> optionalSalary = repository.findSalaryByIdAndDeletedFalse(id);

        if (optionalSalary.isPresent()) {
            Salary oldSalary = optionalSalary.get();
            oldSalary.setDeleted(true);
            return repository.save(oldSalary);

        } else {
            throw new NoContentException("Salary not found !");
        }
    }

    @Override
    public List<Salary> findSalaryByDeletedFalse() throws Exception {
        return repository.findSalaryByDeletedFalse();
    }

    @Override
    public Optional<Salary> findSalaryByIdAndDeletedFalse(Long id) throws NoContentException{
        Optional<Salary> optional = repository.findSalaryByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("Salary not found !");
        }
    }

    @Override
    public Optional<Salary> findSalaryByYearAndDeletedFalse(Integer year) throws NoContentException {
        Optional<Salary> optional = repository.findSalaryByYearAndDeletedFalse(year);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("Salary not found !");
        }
    }
}
