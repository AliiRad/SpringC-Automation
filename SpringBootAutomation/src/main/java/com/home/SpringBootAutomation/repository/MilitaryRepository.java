package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.Military;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface MilitaryRepository extends JpaRepository<Military,Long> {

//    @Modifying
//    @Query("update MilitaryEntity oo set oo.deleted=true where oo.id=:id")
//    void MilitaryDeleted(Long id);
//
//    @Query("select p.name from MilitaryEntity  p where p.id  in :id")
//    List<String> findAllNamesById(@Param("id") ArrayList<Long> id);

    @Query("SELECT oo from MilitaryEntity oo where oo.issuanceDate=:issuanceDate")
    List<Military> findByIssuanceDate(LocalDate issuanceDate);

//    List<Military>findByName(String name);
//
//    List<Military>findByNameAndLastname(String name, String lastname);

//    List<Military> findByLastname(String lastname);

    List<Military>findBySerialNumber(String serialNumber);

//    List<Military>findAllByNameAndLicenseSuspension(String name, boolean deleted);

}
