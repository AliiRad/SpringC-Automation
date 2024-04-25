package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Military;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.repository.MilitaryRepository;
import com.home.SpringBootAutomation.repository.PersonRepository;
import com.home.SpringBootAutomation.service.MilitaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


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
    public Military edit(Military military) {
        log.info("Service-Military-Edit");
        if (findById(military.getId()) != null) {
            militaryRepository.save(military);
            return military;
        } else return null;
    }

    @Override
    public Military remove(Military military) {
        log.info("Service-Military-Remove");
        if (findById(military.getId()) != null) {
            militaryRepository.delete(military);
            return military;
        } else return null;
    }

    @Override
    public Military removeById(Long id) {
        log.info("Service-Military-removeById");
        Military military = findById(id);
        if (military != null) {
            military.setDeleted(true);
            return military;
        } else return null;
    }

    public Military militaryVitiation(Long id) {
        log.info("Service-Military-militaryVitiation");
        Military military = findById(id);
        if (military != null) {
            military.setMilitaryVitiation(true);
            return military;
        } else return null;
    }

    @Override
    public List<Military> findAll() {
        log.info("Service-Military-FindAll");
        List<Military> militaryList = militaryRepository.findAll();
        return militaryList;
    }

    @Override
    public Military findById(Long id) {
        log.info("Service-Military-FindById");
        Optional<Military> military = militaryRepository.findById(id);
        return (military.orElse(null));
    }

    public Optional<Military> findBySerialNumber(String serialNumber) {
        log.info("Service-Military-FindBySerialNumber");
        return militaryRepository.findBySerialNumber(serialNumber);
    }

    public Military findByNationalId(String nationalId) throws NoContentException {
        log.info("Service-Military-findByNationalId : " + nationalId);
        Optional<Person> person = personRepository.findPersonByNationalIdAndDeletedFalse(nationalId);
        if (person.isPresent()) {
            return person.get().getMilitary();
        } else {
            throw new NoContentException("No Driving License");
        }
    }

    public List<Military> findByIssuanceDate(LocalDate issuanceDate) {
        log.info("Service-Military-FindByIssuanceDate");
        return militaryRepository.findByIssuanceDate(issuanceDate);
    }
}

