package com.home.SpringBootAutomation.service.Impl;

import com.home.SpringBootAutomation.Model.DrivingLicenceModel;
import com.home.SpringBootAutomation.repository.DrivingLicenceRepository;
import com.home.SpringBootAutomation.service.DrivingLicenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DrivingLicenceServiceImpl implements DrivingLicenceService {

    private DrivingLicenceModel drivingLicenceModel;
    private final DrivingLicenceRepository drivingLicenceRepository;

    public DrivingLicenceServiceImpl( DrivingLicenceRepository drivingLicenceRepository){

        this.drivingLicenceRepository = drivingLicenceRepository;
    }

    @Override
    public DrivingLicenceModel save(DrivingLicenceModel drivingLicenceModel){
        log.info("Service-DrivingLicenceModel-Save");
        drivingLicenceRepository.save(this.drivingLicenceModel);
        return drivingLicenceModel;
    }

    @Override
    public DrivingLicenceModel edit(DrivingLicenceModel drivingLicenceModel){
        log.info("Service-DrivingLicenceModel-Edit");
        if(findById(drivingLicenceModel.getId())!=null){
            drivingLicenceRepository.save(drivingLicenceModel);
            return drivingLicenceModel;
        }
        else return null;
    }

    @Override
    public DrivingLicenceModel remove(DrivingLicenceModel drivingLicenceModel) {
        log.info("Service-DrivingLicenceModel-Remove");
        if (findById(drivingLicenceModel.getId())!=null){
            drivingLicenceRepository.delete(drivingLicenceModel);
            return drivingLicenceModel;
        }
        else return null;
    }

    public DrivingLicenceModel removeById(Long id){
        log.info("Service-DrivingLicenceModel-removeById");
        DrivingLicenceModel drivingLicenceModel =findById(id);
        if (drivingLicenceModel !=null){
            drivingLicenceModel.setLicenseSuspension(true);
//            drivingLicenceRepository.LicenseSuspension(id);
            return drivingLicenceModel;
        }
        else return null;
    }


    public DrivingLicenceModel licenseSuspension(Long id){
        log.info("Service-DrivingLicenceModel-LicenseSuspension");
        DrivingLicenceModel drivingLicenceModel =findById(id);
        if (drivingLicenceModel !=null){
            drivingLicenceModel.setLicenseSuspension(true);
//            drivingLicenceRepository.LicenseSuspension(id);
            return drivingLicenceModel;
        }
        else return null;
    }


    @Override
    public List<DrivingLicenceModel> findAll(){
        log.info("Service-DrivingLicenceModel-FindAll");
        return drivingLicenceRepository.findAll();
    }


    @Override
    public DrivingLicenceModel findById(Long id){
        log.info("Service-DrivingLicenceModel-FindById");
        Optional<DrivingLicenceModel> drivingLicence = drivingLicenceRepository.findById(id);
        return (drivingLicence.isPresent() ? drivingLicence.get() : null);

    }
    public List<DrivingLicenceModel> findBySerialNumber(String serialNumber){
        log.info("Service-DrivingLicenceModel-findBySerialNumber");
        return drivingLicenceRepository.findBySerialNumber(serialNumber);

    }
    public List<DrivingLicenceModel>findByDate(LocalDateTime issuanceDate){
        log.info("Service-DrivingLicenceModel-findByDate");
        return drivingLicenceRepository.findByDate(issuanceDate);

    }

}
