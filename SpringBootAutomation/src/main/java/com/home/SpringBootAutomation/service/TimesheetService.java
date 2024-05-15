package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Timesheet;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface TimesheetService {
    Timesheet save(Timesheet timesheet);
    Timesheet update(Timesheet timesheet) throws NoContentException;
    Timesheet remove(Long id) throws NoContentException;

    @Transactional
    Timesheet logicalRemove(Long id) throws NoContentException;

    List<Timesheet> findAll();
    Timesheet findById(Long id) throws NoContentException;
    Long getTimesheetCount();

    Timesheet logicalRemoveWithReturn(Long id) throws NoContentException;

    List<Timesheet> findTimesheetByDeletedFalse();
    Timesheet findTimesheetByIdAndDeletedFalse(Long id) throws NoContentException;

    Timesheet findTimesheetByEmployeeIdAndDateAndDeletedFalse(Long id, LocalDate date) throws NoContentException;
    List<Timesheet> findTimesheetByEmployeeIdAndDeletedFalse(Long id) throws NoContentException;
    List<Timesheet> findTimesheetByManagerIdAndDeletedFalse(Long id) throws NoContentException;

    Long countByDeletedFalse();

}
