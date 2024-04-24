package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    List<Timesheet> findAllByDeletedFalse();

    @Transactional
    @Modifying
    @Query("update timesheetEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);


}
