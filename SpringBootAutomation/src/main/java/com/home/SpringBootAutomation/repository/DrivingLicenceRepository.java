package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.enums.TypeOfCertification;
import com.home.SpringBootAutomation.model.DrivingLicence;
import com.home.SpringBootAutomation.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface DrivingLicenceRepository extends JpaRepository<DrivingLicence, Long> {

    Optional<DrivingLicence> findById(Long id);
    List<DrivingLicence> findAll();
    List<DrivingLicence> findAllByDeletedFalse();
    List<DrivingLicence> findAllByDeletedTrue();
    //:todo:Does it true?
    List<DrivingLicence> findAllByTypeOfCertification(TypeOfCertification typeOfCertification);

    //:todo:Does it true?
//    List<DrivingLicence> findByNationalID(String nationalId);
//    List<DrivingLicence> findDrivingLicenceByNatio

    //:todo:Does it true?
    List<DrivingLicence> findByIssuanceDate(LocalDate issuanceDate);

    Optional<DrivingLicence> findBySerialNumber(String serialNumber);


    //:todo:Does it true?
    List<DrivingLicence> findAllByLicenseSuspensionDate(LocalDate licenseSuspensionDate);

    //:todo:Does it true?
    List<DrivingLicence> findAllByEndDate(LocalDate endDate);

//    List<DrivingLicence>findByDate(LocalDate issuanceDate);

    //:todo:Does it true?
    List<DrivingLicence> findAllByRenewal(LocalDate renewal);

    //:todo:Does it true?
    List<DrivingLicence> findByPerson(Person person);

    @Modifying
    @Query("update drivingLicenceEntity  oo set oo.licenseSuspension=true where oo.id=:id")
    void logicalLicenseSuspensionTrue(Long id);

    @Modifying
    @Query("update drivingLicenceEntity  oo set oo.licenseSuspension=false where oo.id=:id")
    void logicalLicenseSuspensionFalse(Long id);

    @Modifying
    @Query("update drivingLicenceEntity  oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Long countByDeletedFalse();

}
