package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.AppointmentDecree;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AppointmentDecreeService {
    AppointmentDecree save(AppointmentDecree appointmentDecree);

    AppointmentDecree update(AppointmentDecree appointmentDecree) throws NoContentException;

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<AppointmentDecree> findAll();

    AppointmentDecree findById(Long id);

    Long getAppointmentDecreeCount();

    AppointmentDecree logicalRemoveWithReturn(Long id);

    List<AppointmentDecree> findAppointmentDecreeByDeletedFalse();

    Optional<AppointmentDecree> findAppointmentDecreeByIdAndDeletedFalse(Long id);

    Long countByDeletedFalse();
}
