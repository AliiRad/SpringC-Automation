package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.model.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person save(Person person);
    Person update(Long id,Person person);

    @Transactional
    void logicalRemove(Long id);

    List<Person> findAll();
    Person findById(Long id);
    Long getPersonsCount();

    Person logicalRemoveWithReturn(Long id);

    //-------------------------------------------------------------------
    //deletedFalse

    List<Person> findPersonByDeletedFalse();
    Optional<Person> findPersonByIdAndDeletedFalse(Long id);

    List<Person> findPersonByNameAndLastnameAndDeletedFalse(String name , String lastName);
    Optional<Person> findPersonByNationalIdAndDeletedFalse(String nationalId);
    Optional<Person> findPersonByUserNameAndDeletedFalse(String UserName);
    List<Person> findPersonByCityAndProvinceAndDeletedFalse(String city , String province );

    Long countByDeletedFalse();

}
