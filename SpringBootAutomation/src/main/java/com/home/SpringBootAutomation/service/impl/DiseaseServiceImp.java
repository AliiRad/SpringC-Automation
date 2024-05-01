package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Disease;
import com.home.SpringBootAutomation.repository.DiseaseRepository;
import com.home.SpringBootAutomation.service.DiseaseService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DiseaseServiceImp implements DiseaseService {
    private final DiseaseRepository diseaseRepository;

    public DiseaseServiceImp(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Override
    public Disease save(Disease disease) {
        log.info("Disease_Save_Service");
        diseaseRepository.save(disease);
        return disease;
    }

    @Override
    @Transactional
    public Disease edit(Disease disease) {
        log.info("Disease_Edit_Service");
        if (findById(disease.getId()) != null) {
            diseaseRepository.save(disease);
            return disease;
        } else {
            return null;
        }
    }

    @Override
    public Disease remove(Disease disease) {
        log.info("Disease_Remove_Service");
        if (findById(disease.getId()) != null) {
            diseaseRepository.delete(disease);
            return disease;
        } else {
            return null;
        }
    }

    @Override
    public Disease logicalRemove(Disease disease) throws NoContentException {
        return null;
    }

    @Override
    @Transactional
    public Disease logicalRemove(Long id) throws NoContentException {
        log.info("Disease_LogicalRemove_Service");
        Disease disease = findById(id);
        if (disease != null) {
            diseaseRepository.save(disease);
            disease.setDeleted(true);
            return disease;
        } else throw new NoContentException("Disease Not Found !");
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
    public Disease findById(Long id) {
        log.info("Disease_FindById_Service");
        Optional<Disease> disease = diseaseRepository.findById(id);
        return (disease.isPresent() ? disease.get() : null);
    }
}
