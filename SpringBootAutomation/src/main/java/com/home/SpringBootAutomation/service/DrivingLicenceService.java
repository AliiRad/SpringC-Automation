package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.DrivingLicence;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface DrivingLicenceService {

    DrivingLicence save(DrivingLicence drivingLicence);

    DrivingLicence update(DrivingLicence drivingLicence) throws NoContentException;

    @Transactional
    DrivingLicence logicalRemove(Long id) throws NoContentException;

    List<DrivingLicence> findAll();

    DrivingLicence findByIdAndDeletedFalse(Long id) throws NoContentException;

    DrivingLicence findById(Long id) throws NoContentException;
}
