package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Timesheet;
import com.home.SpringBootAutomation.repository.TimesheetRepository;
import com.home.SpringBootAutomation.service.TimesheetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void update(Timesheet timesheet) throws Exception {
        Optional<Timesheet> optionalTimesheet = repository.findTimesheetByIdAndDeletedFalse(timesheet.getId());

        if (optionalTimesheet.isPresent()) {
            repository.save(timesheet);
        } else {
            throw new NoContentException("Timesheet not found !");
        }
    }

    @Transactional
    @Override
    public void logicalRemove(Long id) throws Exception {
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
    public Timesheet findById(Long id) throws Exception {
        Optional<Timesheet> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Timesheet> findTimesheetByDeletedFalse() throws Exception {
        return repository.findTimesheetByDeletedFalse();
    }

    @Override
    public Optional<Timesheet> findTimesheetByIdAndDeletedFalse(Long id) throws Exception {
        Optional<Timesheet> optional = repository.findTimesheetByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else return Optional.empty();
    }
}
