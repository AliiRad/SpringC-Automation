package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease,Long> {
}
