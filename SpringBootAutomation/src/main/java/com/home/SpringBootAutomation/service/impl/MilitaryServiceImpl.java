package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.Model.MilitaryServiceModel;
import com.home.SpringBootAutomation.repository.MilitaryRepository;
import com.home.SpringBootAutomation.service.MilitaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MilitaryServiceImpl implements MilitaryService {

    private MilitaryServiceModel militaryServiceModel;
    private final MilitaryRepository militaryRepository;

    public MilitaryServiceImpl(MilitaryRepository militaryRepository) {
        this.militaryRepository = militaryRepository;
    }


    @Override
    public MilitaryServiceModel save(MilitaryServiceModel militaryServiceModel){
        log.info("Service-MilitaryServiceModel-Save");
        militaryRepository.save(this.militaryServiceModel);
        return militaryServiceModel;
    }

    @Override
    public MilitaryServiceModel edit(MilitaryServiceModel militaryServiceModel){
        log.info("Service-MilitaryServiceModel-Edit");
        if(findById(militaryServiceModel.getId())!= null){
            militaryRepository.save(militaryServiceModel);
            return militaryServiceModel;
        }
        else return null;
    }

    @Override
    public MilitaryServiceModel remove(MilitaryServiceModel militaryServiceModel) {
        log.info("Service-MilitaryServiceModel-Remove");
        if (findById(militaryServiceModel.getId())!= null){
            militaryRepository.delete(militaryServiceModel);
            return militaryServiceModel;
        }
        else return null;
    }
    @Override
    public MilitaryServiceModel removeById(Long id){
        log.info("Service-MilitaryServiceModel-removeById");
        MilitaryServiceModel militaryServiceModel =findById(id);
        if (militaryServiceModel !=null){
            militaryServiceModel.setDeleted(true);
//            militaryRepository.MilitaryDeleted(id);
            return militaryServiceModel;
        }
        else return null;
    }

    public MilitaryServiceModel militaryVitiation(Long id){
        log.info("Service-MilitaryServiceModel-militaryVitiation");
        MilitaryServiceModel militaryServiceModel =findById(id);
        if (militaryServiceModel !=null){
            militaryServiceModel.setMilitaryVitiation(true);
            return militaryServiceModel;
        }
        else return null;
    }

    @Override
    public List<MilitaryServiceModel> findAll(){
        log.info("Service-MilitaryServiceModel-FindAll");
        return militaryRepository.findAll();
    }

    @Override
    public MilitaryServiceModel findById(Long id){
        log.info("Service-MilitaryServiceModel-FindById");
        Optional<MilitaryServiceModel> military=militaryRepository.findById(id);
        return (military.orElse(null));
    }

    public List<MilitaryServiceModel>findBySerialNumber(String serialNumber) {
        log.info("Service-MilitaryServiceModel-FindBySerialNumber");
        return militaryRepository.findBySerialNumber(serialNumber);

    }

    public List<MilitaryServiceModel>findByIssuanceDate(LocalDateTime issuanceDate){
        log.info("Service-MilitaryServiceModel-FindByIssuanceDate");
        return militaryRepository.findByIssuanceDate(issuanceDate);
    }
}

