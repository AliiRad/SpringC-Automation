package com.home.SpringBootAutomation.service;


import com.home.SpringBootAutomation.model.DrivingLicence;

import java.util.List;

public interface DrivingLicenceService {
    DrivingLicence save(DrivingLicence drivingLicence);
    DrivingLicence edit(DrivingLicence drivingLicence);
    DrivingLicence remove (DrivingLicence drivingLicence);

    DrivingLicence licenseSuspension(Long id);
    List<DrivingLicence> findAll();
    DrivingLicence findById(Long id);
}
