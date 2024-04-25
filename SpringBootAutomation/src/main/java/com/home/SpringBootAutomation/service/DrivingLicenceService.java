package com.home.SpringBootAutomation.service;


import com.home.SpringBootAutomation.enums.TypeOfCertification;
import com.home.SpringBootAutomation.model.DrivingLicence;

import java.util.List;

public interface DrivingLicenceService {
    DrivingLicence save(DrivingLicence drivingLicence);
    DrivingLicence edit(DrivingLicence drivingLicence);
    DrivingLicence remove (DrivingLicence drivingLicence);
    DrivingLicence removeById (Long id);
    DrivingLicence licenseSuspensionTrue(Long id);
    DrivingLicence licenseSuspensionFalse(Long id);

    List<DrivingLicence> findAll();
    List<DrivingLicence> findAllByTypeOfCertification(TypeOfCertification typeOfCertification);
    DrivingLicence findById(Long id);
}
