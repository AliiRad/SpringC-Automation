package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    @Modifying
    @Query("update timesheetEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    List<Timesheet> findTimesheetByDeletedFalse();

    Optional<Timesheet> findTimesheetByIdAndDeletedFalse(Long id);

}
