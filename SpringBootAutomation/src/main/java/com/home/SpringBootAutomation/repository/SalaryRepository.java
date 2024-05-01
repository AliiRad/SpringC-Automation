package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Long> {
    @Modifying
    @Query("update salaryEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    List<Salary> findSalaryByDeletedFalse();

    Optional<Salary> findSalaryByIdAndDeletedFalse(Long id);

    Optional<Salary> findSalaryByYearAndDeletedFalse(Integer year);

    Long countByDeletedFalse();

}
