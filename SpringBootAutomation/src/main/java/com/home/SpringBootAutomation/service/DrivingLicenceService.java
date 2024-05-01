package com.home.SpringBootAutomation.service;



import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.DrivingLicence;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


public interface DrivingLicenceService {

    DrivingLicence save(DrivingLicence drivingLicence);

    DrivingLicence update(DrivingLicence drivingLicence) throws NoContentException;

    DrivingLicence remove(DrivingLicence drivingLicence) throws NoContentException;

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<DrivingLicence> findAll();

    Optional<DrivingLicence> findByIdAndDeletedFalse(Long id) throws NoContentException;//chek shavad.

    Optional<DrivingLicence> findById(Long id) throws NoContentException;
}
