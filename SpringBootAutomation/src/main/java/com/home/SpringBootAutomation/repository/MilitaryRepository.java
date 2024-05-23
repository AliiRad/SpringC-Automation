package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.enums.MilitaryExemption;
import com.home.SpringBootAutomation.model.Military;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface MilitaryRepository extends JpaRepository<Military,Long> {

    @Modifying
    @Query("update militaryEntity  oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    @Modifying
    @Query("update militaryEntity  oo set oo.militaryVitiation=true where oo.id=:id")
    void logicalMilitaryVitiation(Long id);


    //fetches all active military.

    List<Military> findMilitariesByDeletedFalse();

    Optional<Military> findMilitariesByIdAndDeletedFalse(Long id);

    //:todo:Does it true?
    List<Military> findMilitaryByPersonNameAndPersonLastnameAndDeletedFalse(String name,String lastname);

    Optional<Military>findMilitariesByPersonNationalIdAndDeletedFalse(String nationalId);


    Long countByDeletedFalse();


    List<Military> findAllByDeletedTrue();

    //:todo:Does it true?
    List<Military> findAllByExemption(MilitaryExemption militaryExemption);

    //:todo:Does it true?
    List<Military> findByIssuanceDate(LocalDate issuanceDate);

    Optional<Military> findBySerialNumber(String serialNumber);

    List<Military> findAllByMilitaryVitiationTrue();

    Optional<Military>findMilitariesBySerialNumberAndDeletedFalse(String serialNumber);

    //:todo:Does it true?
    List<Military> findAllByVitiationDate(LocalDate vitiationDate);



}
