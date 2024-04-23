//package com.home.SpringBootAutomation.service.impl;
//
//
//import com.home.SpringBootAutomation.model.DrivingLicence;
//import com.home.SpringBootAutomation.repository.DrivingLicenceRepository;
//import com.home.SpringBootAutomation.service.DrivingLicenceService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Slf4j
//public class DrivingLicenceServiceImpl implements DrivingLicenceService {
//    private DrivingLicenceRepository drivingLicenceRepository;
//
//    public DrivingLicenceServiceImpl( DrivingLicenceRepository drivingLicenceRepository){
//
//        this.drivingLicenceRepository = drivingLicenceRepository;
//    }
//
//    @Override
//    public DrivingLicence save(DrivingLicence drivingLicence){
//        log.info("Service-DrivingLicence-Save");
//        drivingLicenceRepository.save(drivingLicence);
//        return drivingLicence;
//    }
//
//    @Override
//    public DrivingLicence edit(DrivingLicence drivingLicence){
//        log.info("Service-DrivingLicence-Edit");
//        if(findById(drivingLicence.getId())!=null){
//            drivingLicenceRepository.save(drivingLicence);
//            return drivingLicence;
//        }
//        else return null;
//    }
//
//    @Override
//    public DrivingLicence remove(DrivingLicence drivingLicence) {
//        log.info("Service-DrivingLicence-Remove");
//        if (findById(drivingLicence.getId())!=null){
//            drivingLicenceRepository.delete(drivingLicence);
//            return drivingLicence;
//        }
//        else return null;
//    }
//
//    public DrivingLicence removeById(Long id){
//        log.info("Service-DrivingLicence-removeById");
//        DrivingLicence drivingLicence =findById(id);
//        if (drivingLicence !=null){
//            drivingLicence.setLicenseSuspension(true);
//            return drivingLicence;
//        }
//        else return null;
//    }
//
//
//    public DrivingLicence licenseSuspension(Long id){
//        log.info("Service-DrivingLicence-LicenseSuspension");
//        DrivingLicence drivingLicence =findById(id);
//        if (drivingLicence !=null){
//            drivingLicence.setLicenseSuspension(true);
//            return drivingLicence;
//        }
//        else return null;
//    }
//
//
//    @Override
//    public List<DrivingLicence> findAll(){
//        log.info("Service-DrivingLicence-FindAll");
//        List<DrivingLicence> drivingLicenceList =drivingLicenceRepository.findAll();
//        return drivingLicenceList;
//    }
//
//
//    @Override
//    public DrivingLicence findById(Long id){
//        log.info("Service-DrivingLicence-FindById");
//        Optional<DrivingLicence> drivingLicence = drivingLicenceRepository.findById(id);
//        return (drivingLicence.isPresent() ? drivingLicence.get() : null);
//
//    }
//    public List<DrivingLicence> findBySerialNumber(String serialNumber){
//        log.info("Service-DrivingLicence-findBySerialNumber");
//        return drivingLicenceRepository.findBySerialNumber(serialNumber);
//
//    }
//    public List<DrivingLicence>findByDate(LocalDate issuanceDate){
//        log.info("Service-DrivingLicence-findByDate");
//        return drivingLicenceRepository.findByDate(issuanceDate);
//
//    }
//
//}
