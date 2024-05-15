package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.AppointmentDecree;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AppointmentDecreeService {
    AppointmentDecree save(AppointmentDecree appointmentDecree);

    AppointmentDecree edit(AppointmentDecree appointmentDecree) throws NoContentException;

    @Transactional
    AppointmentDecree logicalRemove(Long id) throws NoContentException;

    List<AppointmentDecree> findAll();

    Optional<AppointmentDecree> findById(Long id) throws NoContentException;

    Long getAppointmentDecreeCount();

    AppointmentDecree logicalRemoveWithReturn(Long id) throws NoContentException;

    List<AppointmentDecree> findAppointmentDecreeByDeletedFalse();

    Optional<AppointmentDecree> findAppointmentDecreeByIdAndDeletedFalse(Long id) throws NoContentException;

    Long countByDeletedFalse();
}
