package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.model.MilitaryServiceModel;
import com.home.SpringBootAutomation.repository.MilitaryRepository;
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

    public MilitaryServiceImpl(MilitaryRepository militaryRepository) {
        this.militaryRepository = militaryRepository;
    }


    @Override
    public MilitaryServiceModel save(MilitaryServiceModel militaryServiceModel){
        log.info("Service-MilitaryServiceModel-Save");
        militaryRepository.save(militaryServiceModel);
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
        List<MilitaryServiceModel>militaryServiceModelList=militaryRepository.findAll();
        return militaryServiceModelList;
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

    public List<MilitaryServiceModel>findByIssuanceDate(LocalDate issuanceDate){
        log.info("Service-MilitaryServiceModel-FindByIssuanceDate");
        return militaryRepository.findByIssuanceDate(issuanceDate);
    }
}

