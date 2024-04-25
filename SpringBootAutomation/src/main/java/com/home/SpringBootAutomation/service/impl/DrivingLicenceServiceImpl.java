package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.enums.TypeOfCertification;
import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.DrivingLicence;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.repository.DrivingLicenceRepository;
import com.home.SpringBootAutomation.repository.PersonRepository;
import com.home.SpringBootAutomation.service.DrivingLicenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DrivingLicenceServiceImpl implements DrivingLicenceService {
    private final DrivingLicenceRepository drivingLicenceRepository;
    private final PersonRepository personRepository;

    public DrivingLicenceServiceImpl(DrivingLicenceRepository drivingLicenceRepository, PersonRepository personRepository) {
        this.drivingLicenceRepository = drivingLicenceRepository;
        this.personRepository = personRepository;
    }

    @Override
    public DrivingLicence save(DrivingLicence drivingLicence) {
        log.info("Service-DrivingLicence-Save");
        drivingLicenceRepository.save(drivingLicence);
        return drivingLicence;
    }

    @Override
    public DrivingLicence edit(DrivingLicence drivingLicence) {
        log.info("Service-DrivingLicence-Edit");
        if (findById(drivingLicence.getId()) != null) {
            drivingLicenceRepository.save(drivingLicence);
            return drivingLicence;
        } else return null;
    }

    @Override
    public DrivingLicence remove(DrivingLicence drivingLicence) {
        log.info("Service-DrivingLicence-Remove");
        if (findById(drivingLicence.getId()) != null) {
            drivingLicenceRepository.delete(drivingLicence);
            return drivingLicence;
        } else return null;
    }

    @Override
    public DrivingLicence removeById(Long id) {
        log.info("Service-DrivingLicence-removeById");
        DrivingLicence drivingLicence = findById(id);
        if (drivingLicence != null) {
            drivingLicence.setLicenseSuspension(true);
            return drivingLicence;
        } else return null;
    }

    @Override
    public DrivingLicence licenseSuspensionTrue(Long id) {
        log.info("Service-DrivingLicence-LicenseSuspensionTrue");
        DrivingLicence drivingLicence = findById(id);
        if (drivingLicence != null) {
            drivingLicence.setLicenseSuspension(true);
            return drivingLicence;
        } else return null;
    }

    @Override
    public DrivingLicence licenseSuspensionFalse(Long id) {
        log.info("Service-DrivingLicence-LicenseSuspensionFalse");
        DrivingLicence drivingLicence = findById(id);
        if (drivingLicence != null) {
            drivingLicence.setLicenseSuspension(false);
            return drivingLicence;
        } else return null;
    }


    @Override
    public List<DrivingLicence> findAll() {
        log.info("Service-DrivingLicence-FindAll");
        List<DrivingLicence> drivingLicenceList = drivingLicenceRepository.findAll();
        return drivingLicenceList;
    }

    //todo:this method;
    @Override
    public List<DrivingLicence> findAllByTypeOfCertification(TypeOfCertification typeOfCertification) {
        return null;
    }

    @Override
    public DrivingLicence findById(Long id) {
        log.info("Service-DrivingLicence-FindById");
        Optional<DrivingLicence> drivingLicence = drivingLicenceRepository.findById(id);
        return (drivingLicence.isPresent() ? drivingLicence.get() : null);
    }

    public Optional<DrivingLicence> findBySerialNumber(String serialNumber) {
        log.info("Service-DrivingLicence-findBySerialNumber");
        return drivingLicenceRepository.findBySerialNumber(serialNumber);
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

    public List<DrivingLicence> findByDate(LocalDate issuanceDate) {
        log.info("Service-DrivingLicence-findByDate");
        return drivingLicenceRepository.findByDate(issuanceDate);

    }
}
