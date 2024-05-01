package com.home.SpringBootAutomation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.home.SpringBootAutomation.model.Organisation;

public interface OrganisationRepository extends JpaRepository<Organisation, Long>{

    
}
