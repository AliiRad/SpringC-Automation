package com.home.SpringBootAutomation.service;


import com.home.SpringBootAutomation.enums.TypeOfCertification;
import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.DrivingLicence;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DrivingLicenceService {

    DrivingLicence save(DrivingLicence drivingLicence);

    DrivingLicence update(DrivingLicence drivingLicence) throws NoContentException;

    DrivingLicence remove(DrivingLicence drivingLicence);

//    @Transactional
//    void logicalRemove(Long id) throws NoContentException;

    List<DrivingLicence> findAll();

    DrivingLicence findById(Long id) throws NoContentException;

    Long getDrivingLicenceCount();

    DrivingLicence logicalRemoveWithReturn(Long id) throws NoContentException;

    //deletedFalse

    List<DrivingLicence>findDrivingLicenceByDeletedFalse();

    Optional<DrivingLicence>findDrivingLicenceByIdAndDeletedFalse(Long id) throws NoContentException;

    DrivingLicence licenseSuspensionTrue(Long id);

    DrivingLicence licenseSuspensionFalse(Long id);

    List<DrivingLicence> findAllByTypeOfCertification(TypeOfCertification typeOfCertification);
}
