package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.Model.AppointmentDecree;
import com.home.SpringBootAutomation.repository.AppointmentDecreeRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentDecreeServiceImp implements AppointmentDecreeService{
    private AppointmentDecreeRepository repository;

    public AppointmentDecreeServiceImp(AppointmentDecreeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(AppointmentDecree appointmentDecree) {
        repository.save(appointmentDecree);
    }

    @Override
    public void edit(AppointmentDecree appointmentDecree) {
        repository.save(appointmentDecree);
    }

    @Override
    public void remove(Long id) {
        repository.deleteAllById(Collections.singleton(id));
    }

    @Override
    public Optional<AppointmentDecree> findById(Long id) {
       return repository.findById(id);
    }

    @Override
    public List<AppointmentDecree> findAll() {
        return repository.findAll();
    }
}
