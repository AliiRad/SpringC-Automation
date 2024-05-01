package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.MedicalHistory;
import com.home.SpringBootAutomation.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory,Long> {
    @Query("select oo from medicalEntity oo where  oo.deleted=false ")
    List<MedicalHistory> findAllDeletedFalse();


}
