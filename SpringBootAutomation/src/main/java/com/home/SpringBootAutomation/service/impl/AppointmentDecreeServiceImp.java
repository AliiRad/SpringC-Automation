package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.AppointmentDecree;
import com.home.SpringBootAutomation.repository.AppointmentDecreeRepository;
import com.home.SpringBootAutomation.service.AppointmentDecreeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentDecreeServiceImp implements AppointmentDecreeService {

    private AppointmentDecreeRepository repository;

    public AppointmentDecreeServiceImp(AppointmentDecreeRepository repository) {
        this.repository = repository;
    }

    @Override
    public AppointmentDecree save(AppointmentDecree appointmentDecree) {
        return repository.save(appointmentDecree);
    }

    @Override
    public AppointmentDecree edit(AppointmentDecree appointmentDecree) throws NoContentException {
        repository.findAppointmentDecreeByIdAndDeletedFalse(appointmentDecree.getId()).orElseThrow(
                () -> new NoContentException("No AppointmentDecree Found with id : " + appointmentDecree.getId())
        );
        return repository.save(appointmentDecree);
    }

    @Transactional
    @Override
    public AppointmentDecree logicalRemove(Long id) throws NoContentException {
        AppointmentDecree appointmentDecree = repository.findAppointmentDecreeByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No AppointmentDecree Found with id : " + id)
        );
        repository.logicalRemove(id);
        return appointmentDecree;
    }

    @Override
    public AppointmentDecree logicalRemoveWithReturn(Long id) throws NoContentException {
        Optional<AppointmentDecree> optionalAppointmentDecree = repository.findAppointmentDecreeByIdAndDeletedFalse(id);

        if (optionalAppointmentDecree.isPresent()) {
            AppointmentDecree oldAppointmentDecree = optionalAppointmentDecree.get();
            oldAppointmentDecree.setDeleted(true);
            return repository.save(oldAppointmentDecree);

        } else {
            throw new NoContentException("AppointmentDecree not found !");
        }
    }

    @Override
    public List<AppointmentDecree> findAll() {
        return repository.findAll();
    }

    @Override
    public List<AppointmentDecree> findAppointmentDecreeByDeletedFalse() {
        return repository.findAppointmentDecreeByDeletedFalse();
    }

    @Override
    public Optional<AppointmentDecree> findById(Long id) throws NoContentException {
        Optional<AppointmentDecree> optional = Optional.ofNullable(repository.findById(id).orElseThrow(
                () -> new NoContentException("No AppointmentDecree Found with id : " + id)
        ));
        return optional;
    }

    @Override
    public Optional<AppointmentDecree> findAppointmentDecreeByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<AppointmentDecree> optional = Optional.ofNullable(repository.findAppointmentDecreeByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No AppointmentDecree Found with id : " + id)
        ));
        return optional;
    }

    @Override
    public Long getAppointmentDecreeCount() {
        return repository.count();
    }

    @Override
    public Long countByDeletedFalse() {
        return repository.countByDeletedFalse();
    }
}
