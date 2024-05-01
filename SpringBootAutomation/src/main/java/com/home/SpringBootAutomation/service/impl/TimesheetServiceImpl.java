package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Timesheet;
import com.home.SpringBootAutomation.repository.TimesheetRepository;
import com.home.SpringBootAutomation.service.TimesheetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TimesheetServiceImpl implements TimesheetService {

    private final TimesheetRepository repository;

    public TimesheetServiceImpl(TimesheetRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Timesheet timesheet) throws Exception {
        repository.save(timesheet);
    }

    @Override
    public void update(Timesheet timesheet) throws NoContentException {
        Optional<Timesheet> optionalTimesheet = repository.findTimesheetByIdAndDeletedFalse(timesheet.getId());

        if (optionalTimesheet.isPresent()) {
            repository.save(timesheet);
        } else {
            throw new NoContentException("Timesheet not found !");
        }
    }

    @Transactional
    @Override
    public void logicalRemove(Long id) throws NoContentException {
        Optional<Timesheet> optionalTimesheet = repository.findTimesheetByIdAndDeletedFalse(id);
        if (optionalTimesheet.isPresent()) {
            repository.logicalRemove(id);
        } else {
            throw new NoContentException("Timesheet not found !");
        }
    }

    @Override
    public List<Timesheet> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Optional<Timesheet> findById(Long id) throws NoContentException {
        Optional<Timesheet> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("Timesheet not found !");
        }
    }

    @Override
    public Long getTimesheetCount() {
        return repository.count();
    }

    @Override
    public Timesheet logicalRemoveWithReturn(Long id) throws NoContentException {
        Optional<Timesheet> optionalTimesheet = repository.findTimesheetByIdAndDeletedFalse(id);

        if (optionalTimesheet.isPresent()) {
            Timesheet oldTimesheet = optionalTimesheet.get();
            oldTimesheet.setDeleted(true);
            return repository.save(oldTimesheet);

        } else {
            throw new NoContentException("Timesheet not found !");
        }
    }

    @Override
    public List<Timesheet> findTimesheetByDeletedFalse() throws Exception {
        return repository.findTimesheetByDeletedFalse();
    }

    @Override
    public Optional<Timesheet> findTimesheetByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Timesheet> optional = repository.findTimesheetByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("Timesheet not found !");
        }
    }

    @Override
    public Optional<Timesheet> findTimesheetByEmployeeIdAndDateAndDeletedFalse(Long id, LocalDate date) throws NoContentException{
        Optional<Timesheet> optional = repository.findTimesheetByEmployeeIdAndDateAndDeletedFalse(id,date);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("Timesheet not found !");
        }
    }

    @Override
    public List<Timesheet> findTimesheetByEmployeeIdAndDeletedFalse(Long id) throws NoContentException{
        return repository.findTimesheetByEmployeeIdAndDeletedFalse(id);
    }

    @Override
    public List<Timesheet> findTimesheetByManagerIdAndDeletedFalse(Long id) throws NoContentException{
        return repository.findTimesheetByManagerIdAndDeletedFalse(id);
    }

    @Override
    public Long countByDeletedFalse() {
        return repository.countByDeletedFalse();
    }
}
