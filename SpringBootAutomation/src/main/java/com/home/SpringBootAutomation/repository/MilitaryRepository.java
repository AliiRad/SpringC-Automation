package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.Model.MilitaryServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface MilitaryRepository extends JpaRepository<MilitaryServiceModel,Long> {

//    @Modifying
//    @Query("update MilitaryEntity oo set oo.deleted=true where oo.id=:id")
//    void MilitaryDeleted(Long id);
//
//    @Query("select p.name from MilitaryEntity  p where p.id  in :id")
//    List<String> findAllNamesById(@Param("id") ArrayList<Long> id);

    @Query("SELECT oo from MilitaryEntity oo where oo.issuanceDate=:issuanceDate")
    List<MilitaryServiceModel> findByIssuanceDate(LocalDate issuanceDate);

//    List<MilitaryServiceModel>findByName(String name);
//
//    List<MilitaryServiceModel>findByNameAndLastname(String name, String lastname);

//    List<MilitaryServiceModel> findByLastname(String lastname);

    List<MilitaryServiceModel>findBySerialNumber(String serialNumber);

//    List<MilitaryServiceModel>findAllByNameAndLicenseSuspension(String name, boolean deleted);

}
