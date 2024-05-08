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
    private final AppointmentDecreeRepository repository;

    public AppointmentDecreeServiceImp(AppointmentDecreeRepository repository) {
        this.repository = repository;
    }

    @Override
    public AppointmentDecree save(AppointmentDecree appointmentDecree) {
        return repository.save(appointmentDecree);
    }

    @Override
    public AppointmentDecree update(AppointmentDecree appointmentDecree) throws NoContentException {
        Optional<AppointmentDecree> optionalAppointmentDecree = repository.findAppointmentDecreeByIdAndDeletedFalse(appointmentDecree.getId());

        if (optionalAppointmentDecree.isPresent()){
            return repository.save(appointmentDecree);
        } else {
            throw new NoContentException("AppointmentDecree not found !");
        }
    }

    @Transactional
    @Override
    public void logicalRemove(Long id) throws NoContentException {
        Optional<AppointmentDecree> optionalAppointmentDecree = repository.findAppointmentDecreeByIdAndDeletedFalse(id);
        if (optionalAppointmentDecree.isPresent()) {
            repository.logicalRemove(id);
        } else {
            throw new NoContentException("AppointmentDecree not found !");
        }
    }

    @Override
    public List<AppointmentDecree> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<AppointmentDecree> findById(Long id) throws NoContentException {
        Optional<AppointmentDecree> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("AppointmentDecree not found !");
        }
    }

    @Override
    public Long getAppointmentDecreeCount() {
        return repository.count();
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
    public List<AppointmentDecree> findAppointmentDecreeByDeletedFalse() {
        return repository.findAppointmentDecreeByDeletedFalse();
    }

    @Override
    public Optional<AppointmentDecree> findAppointmentDecreeByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<AppointmentDecree> optional = repository.findAppointmentDecreeByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("AppointmentDecree not found !");
        }
    }

    @Override
    public Long countByDeletedFalse() {
        return repository.countByDeletedFalse();
    }
}
