package com.home.SpringBootAutomation.service;


import com.home.SpringBootAutomation.model.DrivingLicenceModel;

import java.util.List;

public interface DrivingLicenceService {
    DrivingLicenceModel save(DrivingLicenceModel drivingLicenceModel);
    DrivingLicenceModel edit(DrivingLicenceModel drivingLicenceModel);
    DrivingLicenceModel remove (DrivingLicenceModel drivingLicenceModel);

    DrivingLicenceModel licenseSuspension(Long id);
    List<DrivingLicenceModel> findAll();
    DrivingLicenceModel findById(Long id);
}
