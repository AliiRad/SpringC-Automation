package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.MedicalHistory;
import com.home.SpringBootAutomation.repository.MedicalHistoryRepository;
import com.home.SpringBootAutomation.service.MedicalHistoryService;
import com.home.SpringBootAutomation.service.PersonService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MedicalHistoryServiceImp implements MedicalHistoryService {
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final PersonService personService;
    // TODO: 5/14/2024 injection disease

    public MedicalHistoryServiceImp(MedicalHistoryRepository medicalHistoryRepository, PersonService PersonService) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.personService = PersonService;
    }

    @Override
    public MedicalHistory save(MedicalHistory medicalHistory) throws NoContentException {
        log.info("Medical_Save_Service");
        medicalHistory.setDeleted(false);
        medicalHistoryRepository.save(medicalHistory);
        return medicalHistory;
    }

    @Override
    @Transactional
    public MedicalHistory edit(MedicalHistory medicalHistory) throws NoContentException {
        log.info("Medical_Edit_Service");
        medicalHistoryRepository.findById(medicalHistory.getId()).orElseThrow(
                () -> new NoContentException("No medicalHistory Found with id : " + medicalHistory.getId()));
        return medicalHistoryRepository.save(medicalHistory);

    }

    @Override
    public void remove(MedicalHistory medicalHistory) throws NoContentException {
        log.info("Medical_Remove_Service");
        medicalHistoryRepository.findById(medicalHistory.getId()).orElseThrow(
                () -> new NoContentException("No medicalHistory Found with id : " + medicalHistory.getId()));
        medicalHistoryRepository.delete(medicalHistory);
    }

    @Override
    @Transactional
    public MedicalHistory logicalRemove(Long id) throws NoContentException {
        log.info("Medical_LogicalRemove_Service");
        MedicalHistory medicalHistory = medicalHistoryRepository.findById(id).orElseThrow(
                () -> new NoContentException("No medicalHistory Found with id : " + id));
        medicalHistory.setDeleted(true);
        return medicalHistoryRepository.save(medicalHistory);
    }

    @Override
    public List<MedicalHistory> findAll() {
        log.info("Medical_findAll_Service");
        List<MedicalHistory> medicalHistoryList = medicalHistoryRepository.findAll();
        return medicalHistoryList;
    }

    @Override
    public List<MedicalHistory> findAllDeletedFalse() {
        log.info("Medical_findAllDeletedFalse_Service");
        List<MedicalHistory> medicalList = medicalHistoryRepository.findAllDeletedFalse();
        return medicalList;
    }

    @Override
    public MedicalHistory findById(Long id) throws NoContentException {
        log.info("Medical_findById_Service");
        return medicalHistoryRepository.findById(id).orElseThrow(
                () -> new NoContentException("No medicalHistory Found with id : " + id));
    }
}
