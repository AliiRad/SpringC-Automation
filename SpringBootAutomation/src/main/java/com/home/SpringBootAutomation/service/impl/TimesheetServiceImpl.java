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

    private final TimesheetRepository timesheetRepository;

    public TimesheetServiceImpl(TimesheetRepository timesheetRepository) {
        this.timesheetRepository = timesheetRepository;
    }

    @Override
    public Timesheet save(Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }

    @Override
    public Timesheet update(Timesheet timesheet) throws NoContentException {
        timesheetRepository.findById(timesheet.getId()).orElseThrow(
                () -> new NoContentException("No Timesheet Found with id : " + timesheet.getId())
        );
        return timesheetRepository.save(timesheet);
    }

    @Override
    public Timesheet remove(Long id) throws NoContentException {
        Timesheet timesheet = timesheetRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Timesheet Found with id : " + id)
        );
        timesheetRepository.deleteById(id);
        return timesheet;
    }

    @Transactional
    @Override
    public Timesheet logicalRemove(Long id) throws NoContentException {
        Timesheet timesheet = timesheetRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Timesheet Found with id : " + id)
        );
        timesheetRepository.logicalRemove(id);
        return timesheet;
    }

    @Override
    public List<Timesheet> findAll() {
        return timesheetRepository.findAll();
    }

    @Override
    public Timesheet findById(Long id) throws NoContentException {
        return timesheetRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Timesheet Found with id : " + id)
        );
    }

    @Override
    public Long getTimesheetCount() {
        return timesheetRepository.count();
    }

    @Override
    public Timesheet logicalRemoveWithReturn(Long id) throws NoContentException {
        Optional<Timesheet> optionalTimesheet = timesheetRepository.findTimesheetByIdAndDeletedFalse(id);

        if (optionalTimesheet.isPresent()) {
            Timesheet oldTimesheet = optionalTimesheet.get();
            oldTimesheet.setDeleted(true);
            return timesheetRepository.save(oldTimesheet);

        } else {
            throw new NoContentException("Timesheet not found !");
        }
    }

    @Override
    public List<Timesheet> findTimesheetByDeletedFalse() {
        return timesheetRepository.findTimesheetByDeletedFalse();
    }

    @Override
    public Timesheet findTimesheetByIdAndDeletedFalse(Long id) throws NoContentException {
        return timesheetRepository.findTimesheetByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Timesheet Found with id : " + id)
        );
    }

    @Override
    public Timesheet findTimesheetByEmployeeIdAndDateAndDeletedFalse(Long id, LocalDate date) throws NoContentException{
        return timesheetRepository.findTimesheetByEmployeeIdAndDateAndDeletedFalse(id,date).orElseThrow(
                () -> new NoContentException("No Timesheet Found with employee id : " + id + " and date : " + date)
        );
    }

    @Override
    public List<Timesheet> findTimesheetByEmployeeIdAndDeletedFalse(Long id) throws NoContentException{
        return timesheetRepository.findTimesheetByEmployeeIdAndDeletedFalse(id);
    }

    @Override
    public List<Timesheet> findTimesheetByManagerIdAndDeletedFalse(Long id) throws NoContentException{
        return timesheetRepository.findTimesheetByManagerIdAndDeletedFalse(id);
    }

    @Override
    public Long countByDeletedFalse() {
        return timesheetRepository.countByDeletedFalse();
    }
}
