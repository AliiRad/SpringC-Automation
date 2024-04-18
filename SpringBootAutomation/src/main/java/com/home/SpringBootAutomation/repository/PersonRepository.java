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


    List<Person> findByName(String name);

    List<Person> findByNameAndLastname(String name , String lastname);

    List<Person> findByLastname(String lastname);


    @Query("select p.name from PersonEntity  p where p.id  in :id")
    List<String> findAllNamesById(@Param("id") ArrayList<Long> id);

    List<Person>findAllByNameAndDeleted(String name, boolean deleted);
}
