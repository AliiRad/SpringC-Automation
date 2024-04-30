package com.home.SpringBootAutomation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.SpringBootAutomation.model.Section;

public interface SectionRepository extends JpaRepository<Section,Long>{
    
}
