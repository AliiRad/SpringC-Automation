package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.repository.PersonRepository;
import com.home.SpringBootAutomation.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    // Constructor Injection
    private final PersonRepository repository;
    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person save(Person person) {
        return repository.save(person);
    }

    @Override
    public Person update(Person person) throws NoContentException {
        Optional<Person> optionalPerson = repository.findPersonByIdAndDeletedFalse(person.getId());

        if (optionalPerson.isPresent()) {
            return repository.save(person);
        } else {
            throw new NoContentException("Person not found !");
        }
    }

    @Transactional
    @Override
    public void logicalRemove(Long id) throws NoContentException {
        Optional<Person> optionalPerson = repository.findPersonByIdAndDeletedFalse(id);
        if (optionalPerson.isPresent()) {
            repository.logicalRemove(id);
        } else {
            throw new NoContentException("Person not found !");
        }
    }


    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }


    @Override
    public Person findById(Long id) {
        Optional<Person> optional = repository.findById(id);
        return optional.orElse(null);
    }


    @Override
    public Long getPersonsCount() {
        return repository.count();
    }


    @Override
    public Person logicalRemoveWithReturn(Long id) {
        Optional<Person> optionalPerson = repository.findPersonByIdAndDeletedFalse(id);

        if (optionalPerson.isPresent()) {
            Person oldPerson = optionalPerson.get();
            oldPerson.setDeleted(true);
            return repository.save(oldPerson);

        } else return null;
    }


    @Override
    public List<Person> findPersonByDeletedFalse() {
        return repository.findPersonByDeletedFalse();
    }


    @Override
    public Optional<Person> findPersonByIdAndDeletedFalse(Long id) {
        Optional<Person> optional = repository.findPersonByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else return Optional.empty();
    }


    @Override
    public List<Person> findPersonByNameAndLastnameAndDeletedFalse(String name, String lastName) {
        return repository.findPersonByNameAndLastnameAndDeletedFalse(name, lastName);
    }


    @Override
    public Optional<Person> findPersonByNationalIdAndDeletedFalse(String nationalId) {
        Optional<Person> optional = repository.findPersonByNationalIdAndDeletedFalse(nationalId);
        if (optional.isPresent()) {
            return optional;
        } else return Optional.empty();
    }


    @Override
    public Optional<Person> findPersonByUserNameAndDeletedFalse(String UserName) {
        Optional<Person> optional = repository.findPersonByUsernameAndDeletedFalse(UserName);
        if (optional.isPresent()) {
            return optional;
        } else return Optional.empty();
    }



    @Override
    public Long countByDeletedFalse() {
        return repository.countByDeletedFalse();
    }
}
