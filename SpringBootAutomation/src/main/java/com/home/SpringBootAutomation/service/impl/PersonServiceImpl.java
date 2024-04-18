package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.model.PersonModel;
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
    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonModel save(PersonModel personModel){
        log.info("Service-PersonModel-Save");
        personRepository.save(personModel);
        return personModel;
    }

    @Override
    public PersonModel edit(PersonModel personModel){
        log.info("Service-PersonModel-Edit");
        if (findById(personModel.getId())!=null){
            personRepository.save(personModel);
            return personModel;
        }
        else return null;
    }

    @Override
    public PersonModel remove(PersonModel personModel) {
        log.info("Service-PersonModel-Remove");
        if (findById(personModel.getId())!=null){
            personRepository.delete(personModel);
            return personModel;
        }
        else return null;
    }
    @Override
    public PersonModel removeById(Long id){
        log.info("Service-PersonModel-removeById");
        PersonModel personModel =findById(id);
        if (personModel !=null){
            personModel.setDeleted(true);
//            personRepository.PersonDeleted(id);
            return personModel;
        }
        else return null;
    }

    @Override
    public List<PersonModel> findAll(){
        log.info("Service-PersonModel-FindAll");
        return personRepository.findAll();
    }


    @Override
    public PersonModel findById(Long id){
        log.info("Service-PersonModel-FindById");
        Optional<PersonModel> person=personRepository.findById(id);
        return(person.orElse(null)) ;
    }

    @Override
    public List<PersonModel> findByNationalId(String nationalId) {
        log.info("Service-PersonModel-findByNationalId");
        return personRepository.findByNationalID(nationalId);
    }

    public List<PersonModel>findByBirthDate(LocalDate birthdate){
        log.info("Service-PersonModel-findByBirthDate");
        return personRepository.findByBirthDate(birthdate);
    }
}
