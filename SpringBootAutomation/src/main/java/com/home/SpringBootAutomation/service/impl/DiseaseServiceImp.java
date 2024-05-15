package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Disease;
import com.home.SpringBootAutomation.repository.DiseaseRepository;
import com.home.SpringBootAutomation.repository.MedicalHistoryRepository;
import com.home.SpringBootAutomation.service.DiseaseService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DiseaseServiceImp implements DiseaseService {
    private final DiseaseRepository diseaseRepository;

    public DiseaseServiceImp(DiseaseRepository diseaseRepository, MedicalHistoryRepository medicalHistoryRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Override
    public Disease save(Disease disease) throws NoContentException {
        log.info("Disease_Save_Service");
        disease.setDeleted(false);
        diseaseRepository.save(disease);
        return disease;
    }

    @Override
    @Transactional
    public Disease edit(Disease disease) throws NoContentException {
        log.info("Disease_Edit_Service");
        diseaseRepository.findById(disease.getId()).orElseThrow(
                () -> new NoContentException("No Disease Found with id : " + disease.getId()));
        return diseaseRepository.save(disease);
    }

    @Override
    public void remove(Disease disease) throws NoContentException {
        log.info("Disease_Remove_Service");
        diseaseRepository.findById(disease.getId()).orElseThrow(
                () -> new NoContentException("No Disease Found with id : " + disease.getId()));
        diseaseRepository.delete(disease);
    }

    @Override
    @Transactional
    public Disease logicalRemove(Long id) throws NoContentException {
        log.info("Disease_LogicalRemove_Service");
        Disease disease = diseaseRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Disease Found with id : " + id));
        disease.setDeleted(true);
        return diseaseRepository.save(disease);
    }

    @Override
    public List<Disease> findAll() {
        log.info("Disease_FindAll_Service");
        List<Disease> diseaseList = diseaseRepository.findAll();
        return diseaseList;
    }

    @Override
    public List<Disease> findAllDeletedFalse() {
        log.info("Disease_FindAllDeletedFalse_Service");
        List<Disease> diseaseList = diseaseRepository.findAllDeletedFalse();
        return diseaseList;
    }

    @Override
    public Disease findById(Long id) throws NoContentException {
        log.info("Disease_FindById_Service");
        return diseaseRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Disease Found with id : " + id));
    }

    @Override
    public List<Disease> findDiseaseByName(String name) {
        log.info("Disease_FindByName_Service");
        List<Disease> diseaseList = diseaseRepository.findDiseaseByName(name);
        return diseaseList;
    }

    @Override
    public List<Disease> findDiseaseByType(String type) {
        log.info("Disease_FindByType_Service");
        List<Disease> diseaseList = diseaseRepository.findDiseaseByType(type);
        return diseaseList;

    }
}