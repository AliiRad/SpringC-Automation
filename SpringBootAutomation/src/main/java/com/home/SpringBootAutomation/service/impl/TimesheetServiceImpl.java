package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.model.Timesheet;
import com.home.SpringBootAutomation.repository.TimesheetRepository;
import com.home.SpringBootAutomation.service.TimesheetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimesheetServiceImpl implements TimesheetService {

    private final TimesheetRepository timesheetRepository;

    public TimesheetServiceImpl(TimesheetRepository timesheetRepository) {
        this.timesheetRepository = timesheetRepository;
    }

    @Override
    public void save(Timesheet timesheet) throws Exception {
        timesheetRepository.save(timesheet);
    }

    @Override
    public void edit(Timesheet timesheet) throws Exception {
        timesheetRepository.save(timesheet);
    }

    @Override
    public void logicalRemove(Long id) throws Exception {
        timesheetRepository.logicalRemove(id);
    }


    @Override
    public List<Timesheet> findAll() throws Exception {
        return timesheetRepository.findAll();
    }

    @Override
    public List<Timesheet> findAllByDeletedFalse() throws Exception {
        return timesheetRepository.findAllByDeletedFalse();
    }

    @Override
    public Optional<Timesheet> findById(Long id) throws Exception {
        return timesheetRepository.findById(id);
    }


}
