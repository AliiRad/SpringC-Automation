package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.Model.MedicalHistory;
import com.home.SpringBootAutomation.repository.MedicalHistoryRepository;
import com.home.SpringBootAutomation.service.MedicalHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MedicalHistoryServiceImp implements MedicalHistoryService {
    private MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistoryServiceImp(MedicalHistoryRepository medicalHistoryRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    @Override
    public MedicalHistory save(MedicalHistory medicalHistory) {
        log.info("Medical_Save_Service");
        medicalHistoryRepository.save(medicalHistory);
        return medicalHistory;
    }

    @Override
    public MedicalHistory edit(MedicalHistory medicalHistory) {
        log.info("Medical_Edit_Service");
        if (findById(medicalHistory.getId()) != null) {
            medicalHistoryRepository.save(medicalHistory);
            return medicalHistory;
        } else {
            return null;
        }
    }

    @Override
    public MedicalHistory remove(MedicalHistory medicalHistory) {
        log.info("Medical_Remove_Service");
        if (findById(medicalHistory.getId()) != null) {
            medicalHistoryRepository.delete(medicalHistory);
            return medicalHistory;
        } else {
            return null;
        }
    }

    @Override
    public List<MedicalHistory> findAll() {
        log.info("Medical_findAll_Service");
        List<MedicalHistory> medicalHistoryList = medicalHistoryRepository.findAll();
        return medicalHistoryList;
    }

    @Override
    public MedicalHistory findById(Long id) {
        log.info("Medical_findById_Service");
        Optional<MedicalHistory> medicalHistory = medicalHistoryRepository.findById(id);
        return (medicalHistory.isPresent() ? medicalHistory.get() : null);
    }
}
