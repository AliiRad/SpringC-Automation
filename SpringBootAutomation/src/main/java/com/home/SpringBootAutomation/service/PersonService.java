package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.model.Person;

import java.util.List;

public interface PersonService {
    Person save(Person person);

    Person edit(Person person);

    Person remove(Person person);

    Person removeById(Long id);

    List<Person> findAll();

    Person findById(Long id);

    List<Person> findByNationalId(String nationalID);

    Person findByName(String name);

    Person findByNameAndLastname(String name,String lastname);

}
