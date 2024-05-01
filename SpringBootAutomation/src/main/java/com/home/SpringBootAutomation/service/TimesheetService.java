package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Timesheet;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TimesheetService {
    void save(Timesheet timesheet) throws Exception;
    void update(Timesheet timesheet) throws NoContentException;

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Timesheet> findAll() throws Exception;
    Optional<Timesheet> findById(Long id) throws NoContentException;
    Long getTimesheetCount();

    Timesheet logicalRemoveWithReturn(Long id) throws NoContentException;

    List<Timesheet> findTimesheetByDeletedFalse() throws Exception;
    Optional<Timesheet> findTimesheetByIdAndDeletedFalse(Long id) throws NoContentException;

    Optional<Timesheet> findTimesheetByEmployeeIdAndDateAndDeletedFalse(Long id, LocalDate date) throws NoContentException;
    List<Timesheet> findTimesheetByEmployeeIdAndDeletedFalse(Long id) throws NoContentException;
    List<Timesheet> findTimesheetByManagerIdAndDeletedFalse(Long id) throws NoContentException;

    Long countByDeletedFalse();

}
