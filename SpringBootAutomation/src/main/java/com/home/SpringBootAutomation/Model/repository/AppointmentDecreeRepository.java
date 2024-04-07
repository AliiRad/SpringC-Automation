package com.home.SpringBootAutomation.Model.repository;

import com.home.SpringBootAutomation.Model.entity.AppointmentDecree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentDecreeRepository extends JpaRepository<AppointmentDecree,Long> {
}
