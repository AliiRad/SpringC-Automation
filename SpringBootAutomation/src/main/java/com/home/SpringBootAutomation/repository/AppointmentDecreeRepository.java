package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.AppointmentDecree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentDecreeRepository extends JpaRepository<AppointmentDecree,Long> {
}
