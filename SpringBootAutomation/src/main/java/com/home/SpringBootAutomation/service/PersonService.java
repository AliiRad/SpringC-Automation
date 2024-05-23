package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface PersonService {
    Person save(Person person);
    Person update(Person person) throws NoContentException;

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Person> findAll();
    Optional<Person> findById(Long id) throws NoContentException;

    Long getPersonsCount();

    Person logicalRemoveWithReturn(Long id) throws NoContentException;

    //deletedFalse

    List<Person> findPersonByDeletedFalse();
    Optional<Person> findPersonByIdAndDeletedFalse(Long id) throws NoContentException;
    List<Person> findPersonByNameAndLastnameAndDeletedFalse(String name , String lastName);
    Optional<Person> findPersonByNationalIDAndDeletedFalse(String nationalId) throws NoContentException;


    Long countByDeletedFalse();

}
