package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.model.Timesheet;

import java.util.List;
import java.util.Optional;

public interface TimesheetService {

    void save(Timesheet timesheet) throws Exception;
    void edit(Timesheet timesheet) throws Exception;
    void logicalRemove(Long id) throws Exception;

    List<Timesheet> findAll() throws Exception;
    List<Timesheet> findAllByDeletedFalse() throws Exception;

    Optional<Timesheet> findById(Long id) throws Exception;

}
