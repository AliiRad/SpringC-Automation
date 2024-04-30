package com.home.SpringBootAutomation.service;


import com.home.SpringBootAutomation.enums.TypeOfCertification;
import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.DrivingLicence;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DrivingLicenceService {

    DrivingLicence save(DrivingLicence drivingLicence);

    DrivingLicence update(DrivingLicence drivingLicence) throws NoContentException;

    DrivingLicence remove(DrivingLicence drivingLicence);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<DrivingLicence> findAll();

    DrivingLicence findById(Long id) throws NoContentException;//chek shavad.

    Long getDrivingLicenceCount();

    //deletedFalse

    List<DrivingLicence>findDrivingLicenceByDeletedFalse();

    Optional<DrivingLicence>findByIdAndDeletedFalse(Long id) throws NoContentException;

    Optional<DrivingLicence> licenseSuspensionTrue(Long id);

    Optional<DrivingLicence> licenseSuspensionFalse(Long id);

    List<DrivingLicence> findAllByTypeOfCertification(TypeOfCertification typeOfCertification);

    List<DrivingLicence>findAllByLicenseSuspensionTrue();//todo:balad nistam.

    List<DrivingLicence>findByIssuanceDate(LocalDate issuanceDate);

    Optional<DrivingLicence>findBySerialNumber(String serialNumber);
}
