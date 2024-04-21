package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.model.AppointmentDecree;

import java.util.List;

public interface AppointmentDecreeService {
    AppointmentDecree save(AppointmentDecree appointmentDecree);

    AppointmentDecree edit(AppointmentDecree appointmentDecree);

    AppointmentDecree remove(AppointmentDecree appointmentDecree);

    AppointmentDecree logicalRemove(Long id);

    AppointmentDecree findById(Long id);

    List<AppointmentDecree> findAll();
}
