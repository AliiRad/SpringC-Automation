package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.DrivingLicence;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.repository.DrivingLicenceRepository;
import com.home.SpringBootAutomation.repository.MilitaryRepository;
import com.home.SpringBootAutomation.repository.PersonRepository;
import com.home.SpringBootAutomation.service.DrivingLicenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DrivingLicenceServiceImpl implements DrivingLicenceService {
    private final MilitaryRepository militaryRepository;

    private final DrivingLicenceRepository drivingLicenceRepository;
    private final PersonRepository personRepository;

    public DrivingLicenceServiceImpl(DrivingLicenceRepository drivingLicenceRepository, PersonRepository personRepository,
                                     MilitaryRepository militaryRepository) {
        this.drivingLicenceRepository = drivingLicenceRepository;
        this.personRepository = personRepository;
        this.militaryRepository = militaryRepository;
    }





    @Override
    public DrivingLicence save(DrivingLicence drivingLicence) {
        log.info("Service-DrivingLicence-Save");
        drivingLicenceRepository.save(drivingLicence);
        return drivingLicence;
    }

    @Override
    public DrivingLicence update(DrivingLicence drivingLicence)throws NoContentException {
        log.info("Service-DrivingLicence-Update");
        Optional<DrivingLicence>optionalDrivingLicence=drivingLicenceRepository.findByIdAndDeletedFalse(drivingLicence.getId());
        if (optionalDrivingLicence.isPresent()){
            return drivingLicenceRepository.save(drivingLicence);
        }else {
            throw new NoContentException("DrivingLicence not found !");
        }
    }

    @Override
    public DrivingLicence remove(DrivingLicence drivingLicence) throws NoContentException {
        log.info("Service-DrivingLicence-Remove");
        if (findById(drivingLicence.getId()).isPresent()) {
            drivingLicenceRepository.delete(drivingLicence);
            return drivingLicence;
        } else return null;
    }

    @Override
    public void logicalRemove(Long id) throws NoContentException {
        log.info("Service-DrivingLicence-LogicalRemove");
        Optional<DrivingLicence>optionalDrivingLicence=drivingLicenceRepository.findByIdAndDeletedFalse(id);
        if (optionalDrivingLicence.isPresent()){
            militaryRepository.logicalRemove(id);
        }else {
            throw new NoContentException("DrivingLicense not found !");
        }
    }

    @Override
    public List<DrivingLicence> findAll() {
        log.info("Service-DrivingLicence-FindAll");
        return drivingLicenceRepository.findAll();
    }



    @Override
    public Optional<DrivingLicence> findByIdAndDeletedFalse(Long id) throws NoContentException {
        log.info("Service-DrivingLicence-FindByIdAndDeletedFalse");
        Optional<DrivingLicence> optionalDrivingLicence = drivingLicenceRepository.findByIdAndDeletedFalse(id);
        if (optionalDrivingLicence.isPresent()){
            return optionalDrivingLicence;
        }else {
            throw new NoContentException("DrivingLicence not found !");
        }
    }

    @Override
    public Optional<DrivingLicence> findById(Long id) throws NoContentException {
        log.info("Service-DrivingLicence-FindById");
        Optional<DrivingLicence> optionalDrivingLicence = drivingLicenceRepository.findById(id);
        if (optionalDrivingLicence.isPresent()){
            return optionalDrivingLicence;
        }else {
            throw new NoContentException("DrivingLicence not found !");
        }
    }


    public List<DrivingLicence> findByNationalId(String nationalId) throws NoContentException {
        log.info("Service-DrivingLicence-findByNationalId : " + nationalId);
        Optional<Person> person = personRepository.findPersonByNationalIdAndDeletedFalse(nationalId);
        if (person.isPresent()) {
            return person.get().getDrivingLicences();
        } else {
            throw new NoContentException("No Driving License");
        }
    }

}
