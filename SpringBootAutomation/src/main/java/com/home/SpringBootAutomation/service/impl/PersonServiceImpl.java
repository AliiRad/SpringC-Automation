package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.repository.PersonRepository;
import com.home.SpringBootAutomation.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person save(Person person){
        log.info("Service-Person-Save");
        personRepository.save(person);
        return person;
    }

    @Override
    public Person edit(Person person){
        log.info("Service-Person-Edit");
        if (findById(person.getId())!=null){
            personRepository.save(person);
            return person;
        }
        else return null;
    }

    @Override
    public Person remove(Person person) {
        log.info("Service-Person-Remove");
        if (findById(person.getId())!=null){
            personRepository.delete(person);
            return person;
        }
        else return null;
    }
    @Override
    public Person removeById(Long id){
        log.info("Service-Person-removeById");
        Person person =findById(id);
        if (person !=null){
            person.setDeleted(true);
            return person;
        }
        else return null;
    }

    @Override
    public List<Person> findAll(){
        log.info("Service-Person-FindAll");
        List<Person>personList=personRepository.findAll();
        return personRepository.findAll();
    }


    @Override
    public Person findById(Long id){
        log.info("Service-Person-FindById");
        Optional<Person> person=personRepository.findById(id);
        return(person.orElse(null)) ;
    }

    @Override
    public List<Person> findByNationalId(String nationalId) {
        log.info("Service-Person-findByNationalId");
        return personRepository.findByNationalID(nationalId);
    }

    public List<Person>findByBirthDate(LocalDate birthdate){
        log.info("Service-Person-findByBirthDate");
        return personRepository.findByBirthDate(birthdate);
    }
}
