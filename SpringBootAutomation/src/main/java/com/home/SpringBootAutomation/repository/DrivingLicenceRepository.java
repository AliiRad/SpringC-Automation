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


    //------------------------------------------------------

    Optional<DrivingLicence> findById(Long id);

    List<DrivingLicence>findAll();

    List<DrivingLicence>findAllByDeletedFalse();

    List<DrivingLicence>findAllByDeletedTrue();

    //------------------------------------------------------

    //:todo:Dose it true?
    List<DrivingLicence>findAllByTypeOfCertification(TypeOfCertification typeOfCertification);

    //------------------------------------------------------

    //:todo:Dose it true?
    @Query("SELECT OO FROM DrivingLicenceEntity OO WHERE OO.nationalId=:nationalId")
    List<DrivingLicence>findByNationalID(String nationalId);

    //------------------------------------------------------

    //:todo:Dose it true?
    List<DrivingLicence>findByIssuanceDate(LocalDate issuanceDate);

    //------------------------------------------------------

    Optional<DrivingLicence>findBySerialNumber(String serialNumber);

    //------------------------------------------------------
    List<DrivingLicence>findAllByLicenseSuspensionTrue();

    //------------------------------------------------------

    //:todo:Dose it true?
    List<DrivingLicence>findAllByLicenseSuspensionDate(LocalDate licenseSuspensionDate);

    //------------------------------------------------------

    //:todo:Dose it true?
    List<DrivingLicence>findAllByEndDate(LocalDate endDate);

    //------------------------------------------------------

    //:todo:Dose it true?
    List<DrivingLicence>findAllByRenewal(LocalDate renewal);

    //------------------------------------------------------

    //:todo:Dose it true?
    List<DrivingLicence>findByPerson(Person person);

    //------------------------------------------------------

    @Modifying
    @Query("update DrivingLicenceEntity  oo set oo.licenseSuspension=true where oo.id=:id")
    void logicalLicenseSuspensionTrue(Long id);

    //------------------------------------------------------

    @Modifying
    @Query("update DrivingLicenceEntity  oo set oo.licenseSuspension=false where oo.id=:id")
    void logicalLicenseSuspensionFalse(Long id);

    //------------------------------------------------------

    @Modifying
    @Query("update DrivingLicenceEntity  oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    //------------------------------------------------------

    Long countByDeletedFalse();

}
