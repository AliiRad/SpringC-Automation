package com.home.SpringBootAutomation.Model.service;

import com.home.SpringBootAutomation.Model.entity.AppointmentDecree;

import java.util.List;
import java.util.Optional;

public interface AppointmentDecreeService {
    public void save(AppointmentDecree appointmentDecree);

    public void edit(AppointmentDecree appointmentDecree);

    public void remove(Long id);

    public Optional<AppointmentDecree> findById(Long id);

    public List<AppointmentDecree> findAll();
}
