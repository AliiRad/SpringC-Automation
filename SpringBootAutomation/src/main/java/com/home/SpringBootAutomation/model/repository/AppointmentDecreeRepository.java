package com.home.SpringBootAutomation.model.repository;

import com.home.SpringBootAutomation.model.entity.AppointmentDecree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentDecreeRepository extends JpaRepository<AppointmentDecree,Long> {
}
