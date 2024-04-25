package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.model.Timesheet;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TimesheetService {

    void save(Timesheet timesheet) throws Exception;
    void update(Timesheet timesheet) throws Exception;
    @Transactional
    void logicalRemove(Long id) throws Exception;

    List<Timesheet> findAll() throws Exception;

    Timesheet findById(Long id) throws Exception;
    List<Timesheet> findTimesheetByDeletedFalse() throws Exception;

    Optional<Timesheet> findTimesheetByIdAndDeletedFalse(Long id) throws Exception;

}
