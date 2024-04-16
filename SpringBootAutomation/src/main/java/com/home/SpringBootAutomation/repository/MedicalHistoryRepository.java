package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.Model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory,Long> {
}
