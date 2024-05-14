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

    private final SalaryRepository salaryRepository;

    public SalaryServiceImpl(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @Override
    public Salary save(Salary salary) {
        return salaryRepository.save(salary);
    }

    @Override
    public Salary update(Salary salary) throws NoContentException {
        salaryRepository.findById(salary.getId()).orElseThrow(
                () -> new NoContentException("No Salary Found with id : " + salary.getId())
        );
        return salaryRepository.save(salary);
    }

    @Override
    public Salary remove(Long id) throws NoContentException {
        Salary salary = salaryRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Salary Found with id : " + id)
        );
        salaryRepository.deleteById(id);
        return salary;
    }

    @Transactional
    @Override
    public Salary logicalRemove(Long id) throws NoContentException {
        Salary salary = salaryRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Salary Found with id : " + id)
        );
        salaryRepository.logicalRemove(id);
        return salary;
    }

    @Override
    public List<Salary> findAll() {
        return salaryRepository.findAll();
    }

    @Override
    public Salary findById(Long id) throws NoContentException {
        return salaryRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Salary Found with id : " + id)
        );
    }

    @Override
    public Long getSalariesCount() {
        return salaryRepository.count();
    }

    @Override
    public Salary logicalRemoveWithReturn(Long id) throws NoContentException {
        Optional<Salary> optionalSalary = salaryRepository.findSalaryByIdAndDeletedFalse(id);

        if (optionalSalary.isPresent()) {
            Salary oldSalary = optionalSalary.get();
            oldSalary.setDeleted(true);
            return salaryRepository.save(oldSalary);

        } else {
            throw new NoContentException("Salary not found !");
        }
    }

    @Override
    public List<Salary> findSalaryByDeletedFalse() {
        return salaryRepository.findSalaryByDeletedFalse();
    }

    @Override
    public Salary findSalaryByIdAndDeletedFalse(Long id) throws NoContentException{
        return salaryRepository.findSalaryByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Salary Found with id : " + id)
        );
    }

    @Override
    public Salary findSalaryByYearAndDeletedFalse(Integer year) throws NoContentException {
        return salaryRepository.findSalaryByYearAndDeletedFalse(year).orElseThrow(
                () -> new NoContentException("No Salary Found with year : " + year)
        );
    }
}
