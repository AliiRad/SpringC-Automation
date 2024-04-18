package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public interface PersonRepository extends JpaRepository<Person,Long> {

    @Modifying
    @Query("update PersonEntity oo set oo.deleted=true where oo.id=:id")
    void PersonDeleted(Long id);

    @Query("SELECT oo from PersonEntity oo where oo.nationalId=:nationalId")
    List<Person> findByNationalID(String nationalId);

    @Query("SELECT oo from PersonEntity oo where oo.birthdate=:birthdate")
    List<Person>findByBirthDate(LocalDate birthdate);

    @Query("SELECT oo from PersonEntity oo where oo.name=:name")
    List<Person> findByName(String name);

    @Query("select oo from  PersonEntity oo where oo.name=:name and oo.lastname=:lastname")
    List<Person> findByNameAndLastname(String name , String lastname);


    List<Person> findByLastname(String lastname);


    List<Person>findByUserName(String username);

    List<Person>findByUserNameAndDeletedTrue(String username);

    List<Person>findByCertificateID(String certificateId);

    @Query("select oo from  PersonEntity oo where oo.city=:city and oo.province=:province")
    List<Person>findByCityAndProvince(String city,String province);

    @Query("select oo from PersonEntity oo where oo.status=:true")
    List<Person>findByTrueStatus(boolean status);
    @Query("select oo from PersonEntity oo where oo.deleted=:true")
    List<Person>findByDeleted(boolean deleted);

    @Query("select p.name from PersonEntity  p where p.id  in :id")
    List<String> findAllNamesById(@Param("id") ArrayList<Long> id);

    //TODO:List<Person>findAllByNameAndDeletedTrue(String name, boolean deleted);
}
