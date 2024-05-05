package com.home.SpringBootAutomation.service.impl;
import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.DrivingLicence;
import com.home.SpringBootAutomation.repository.DrivingLicenceRepository;
import com.home.SpringBootAutomation.service.DrivingLicenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class DrivingLicenceServiceImpl implements DrivingLicenceService {

    private final DrivingLicenceRepository drivingLicenceRepository;


    public DrivingLicenceServiceImpl(DrivingLicenceRepository drivingLicenceRepository
                                     ) {
        this.drivingLicenceRepository = drivingLicenceRepository;

    }

    @Override
    public DrivingLicence save(DrivingLicence drivingLicence) {
        log.info("Service-DrivingLicence-Save");
        return drivingLicenceRepository.save(drivingLicence);
    }

    @Override
    public DrivingLicence update(DrivingLicence drivingLicence) throws NoContentException {
        log.info("Service-DrivingLicence-Update");
        drivingLicenceRepository.findById(drivingLicence.getId()).orElseThrow(
                () -> new NoContentException("No DrivingLicence Found with id : " + drivingLicence.getId())
        );
        return drivingLicenceRepository.save(drivingLicence);
    }

    @Override
    public DrivingLicence logicalRemove(Long id) throws NoContentException {
        log.info("Service-DrivingLicence-LogicalRemove");
        DrivingLicence drivingLicence = drivingLicenceRepository.findById(id).orElseThrow(
                () -> new NoContentException("No DrivingLicence Found with id : " + id)
        );
        drivingLicenceRepository.logicalRemove(id);
        return drivingLicence;
    }

    @Override
    public List<DrivingLicence> findAll() {
        log.info("Service-DrivingLicence-FindAll");
        return drivingLicenceRepository.findAll();
    }

    @Override
    public DrivingLicence findByIdAndDeletedFalse(Long id) throws NoContentException {
        log.info("Service-DrivingLicence-FindByIdAndDeletedFalse");
        return drivingLicenceRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No DrivingLicence Found with id : " + id)
        );
    }

    @Override
    public DrivingLicence findById(Long id) throws NoContentException {
        log.info("Service-DrivingLicence-FindById");
        return drivingLicenceRepository.findById(id).orElseThrow(
                () -> new NoContentException("No DrivingLicence Found with id:" + id)
        );
    }


}
