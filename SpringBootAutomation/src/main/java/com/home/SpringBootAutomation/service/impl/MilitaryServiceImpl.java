package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Military;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.repository.MilitaryRepository;
import com.home.SpringBootAutomation.repository.PersonRepository;
import com.home.SpringBootAutomation.service.MilitaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MilitaryServiceImpl implements MilitaryService {

    private final MilitaryRepository militaryRepository;
    private final PersonRepository personRepository;

    public MilitaryServiceImpl(MilitaryRepository militaryRepository, PersonRepository personRepository) {
        this.militaryRepository = militaryRepository;
        this.personRepository = personRepository;
    }

    @Override
    public Military save(Military military) {
        log.info("Service-Military-Save");
        militaryRepository.save(military);
        return military;
    }

    @Override
    public Military update(Military military) throws NoContentException{
        log.info("Service-Military-Update");
        Optional<Military>optionalMilitary=militaryRepository.findMilitariesByIdAndDeletedFalse(military.getId());
        if (optionalMilitary.isPresent()) {
            return militaryRepository.save(military);
        } else {
            throw new NoContentException("Military not found!");
        }
    }


    @Transactional
    @Override
    public void logicalRemove(Long id) throws NoContentException {
        log.info("Service-Military-logicalRemove");
        Optional<Military>optionalMilitary=militaryRepository.findMilitariesByIdAndDeletedFalse(id);
        if (optionalMilitary.isPresent()){
            militaryRepository.logicalRemove(id);
        }else {
            throw new NoContentException("Military not found !");
        }

    }

    @Transactional
    @Override
    public void militaryVitiation(Long id) throws NoContentException {
        log.info("Service-Military-militaryVitiation");
        Optional<Military>optionalMilitary=militaryRepository.findMilitariesByIdAndDeletedFalse(id);
        if (optionalMilitary.isPresent()) {
            militaryRepository.logicalMilitaryVitiation(id);
        }else {
            throw new NoContentException("Military not found !");
        }
    }

    @Override
    public List<Military> findAll() {
        log.info("Service-Military-FindAll");
        return militaryRepository.findAll();
    }

    @Override
    public Optional<Military> findById(Long id) throws NoContentException {
        log.info("Service-Military-FindById");
        Optional<Military> optionalMilitary = militaryRepository.findById(id);
        if (optionalMilitary.isPresent()){
            return optionalMilitary;
        }else {

        }throw new NoContentException("Military not found !");
    }

    @Override
    public Long getMilitaryCount() {
        return militaryRepository.count();
    }


    @Override
    public List<Military> findMilitaryByDeletedFalse() {
        return militaryRepository.findMilitariesByDeletedFalse();
    }

    @Override
    public Optional<Military> findMilitaryByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Military>militaryOptional=militaryRepository.findMilitariesByIdAndDeletedFalse(id);
        if (militaryOptional.isPresent()){
            return militaryOptional;
        }else {
            throw new NoContentException("Military not found");
        }
    }

    @Override
    public Military findMilitaryByNameAndLastnameAndDeletedFalse(String name,String lastname){
        List<Person>personList=personRepository.findPersonByNameAndLastnameAndDeletedFalse(name, lastname);
        return null;
        //todo:do it
    }

    public Military findByNationalId(String nationalId) throws NoContentException {
        log.info("Service-Military-findByNationalId : " + nationalId);
        Optional<Person> person = personRepository.findPersonByNationalIDAndDeletedFalse(nationalId);
        if (person.isPresent()) {
            return person.get().getMilitary();
        } else {
            throw new NoContentException("No Military found");
        }
    }

    @Override
    public Optional<Military> findMilitaryBySerialNumberAndDeletedFalse(String serialNumber) throws NoContentException {
       Optional<Military>militaryOptional=militaryRepository.findMilitariesBySerialNumberAndDeletedFalse(serialNumber);
       if (militaryOptional.isPresent()){
           return militaryOptional;
       }else {
           throw new NoContentException("Military not found !");
       }
    }

    @Override
    public Long countByDeletedFalse() {
        return militaryRepository.countByDeletedFalse();
    }

    public Optional<Military> findBySerialNumber(String serialNumber) {
        log.info("Service-Military-FindBySerialNumber");
        return militaryRepository.findBySerialNumber(serialNumber);
    }

    public List<Military> findByIssuanceDate(LocalDate issuanceDate) {
        log.info("Service-Military-FindByIssuanceDate");
        return militaryRepository.findByIssuanceDate(issuanceDate);
    }
}

