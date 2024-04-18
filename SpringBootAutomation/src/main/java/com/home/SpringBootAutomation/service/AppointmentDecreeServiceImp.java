package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.model.AppointmentDecree;
import com.home.SpringBootAutomation.repository.AppointmentDecreeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentDecreeServiceImp implements AppointmentDecreeService{
    private AppointmentDecreeRepository repository;

    public AppointmentDecreeServiceImp(AppointmentDecreeRepository repository) {
        this.repository = repository;
    }

    @Override
    public AppointmentDecree save(AppointmentDecree appointmentDecree) {
        repository.save(appointmentDecree);
        return appointmentDecree;
    }

    @Override
    public AppointmentDecree edit(AppointmentDecree appointmentDecree) {
        repository.save(appointmentDecree);
        return appointmentDecree;
    }

    @Override
    public AppointmentDecree remove(AppointmentDecree appointmentDecree) {
        if (findById(appointmentDecree.getId()) != null) {
            repository.delete(appointmentDecree);
            return appointmentDecree;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public AppointmentDecree logicalRemove(Long id) {
        AppointmentDecree appointmentDecree = findById(id);
        if (appointmentDecree != null) {
            repository.save(appointmentDecree);
            return appointmentDecree;
        } else{
            return null;
        }
    }

    @Override
    public AppointmentDecree findById(Long id) {
       Optional<AppointmentDecree> appointmentDecree= repository.findById(id);
       return (appointmentDecree.isPresent() ? appointmentDecree.get() : null);
    }

    @Override
    public List<AppointmentDecree> findAll() {
        List<AppointmentDecree> appointmentDecreeList=repository.findAll();
        return appointmentDecreeList;
    }
}
