package com.home.SpringBootAutomation.service.impl;
import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.repository.PersonRepository;
import com.home.SpringBootAutomation.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    // Constructor Injection
    private final PersonRepository repository;
    public PersonServiceImpl(PersonRepository repository){
        this.repository = repository;
    }


    @Override
    public Person save(@Valid Person person) {
        //TODO: Adding DuplicatedException (maybe for user and role)
        return repository.save(person);
    }

    @Override
    public Person update(Person person) throws NoContentException {
        repository.findPersonByIdAndDeletedFalse(person.getId()).orElseThrow(
                () -> new NoContentException("No Active Person Was Found with id "  + person.getId() +" To Update !")
        );
        return repository.save(person);
    }

    @Override
    public void logicalRemove(Long id) throws NoContentException {

        repository.findPersonByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Person Was Found with id " + id  +" To Remove !")
        );
        repository.logicalRemove(id);
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Person> findById(Long id) throws NoContentException {
        Optional<Person> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Person Was Found with id : " + id );
        }


    }

    @Override
    public Long getPersonsCount() {
        return repository.count();
    }

    @Override
    public Person logicalRemoveWithReturn(Long id) throws NoContentException {
        Person person = repository.findPersonByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Person Was Found with id  " + id  +" To Remove !")
        );

        person.setDeleted(true);
        return repository.save(person);

    }

    @Override
    public List<Person> findPersonByDeletedFalse() {
        return repository.findPersonByDeletedFalse();
    }

    @Override
    public Optional<Person> findPersonByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Person> optional = repository.findPersonByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active Person Was Found with id : " + id );
        }
    }

    @Override
    public List<Person> findPersonByNameAndLastnameAndDeletedFalse(String name, String lastName) {
        return repository.findPersonByNameAndLastnameAndDeletedFalse(name , lastName);
    }

    @Override
    public Optional<Person> findPersonByNationalIDAndDeletedFalse(String nationalId) throws NoContentException {
        Optional<Person> optional = repository.findPersonByNationalIdAndDeletedFalse(nationalId);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active Person Was Found with National ID : " + nationalId );
        }
    }

    @Override
    public Long countByDeletedFalse() {
        return repository.countByDeletedFalse();
    }
}
