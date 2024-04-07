package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.Model.AppointmentDecree;

import java.util.List;
import java.util.Optional;

public interface AppointmentDecreeService {
    public void save(AppointmentDecree appointmentDecree);

    public void edit(AppointmentDecree appointmentDecree);

    public void remove(Long id);

    public Optional<AppointmentDecree> findById(Long id);

    public List<AppointmentDecree> findAll();
}
