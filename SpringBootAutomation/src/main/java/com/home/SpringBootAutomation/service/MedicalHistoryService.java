package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.Model.MedicalHistory;

import java.util.List;

public interface MedicalHistoryService {
    MedicalHistory save(MedicalHistory medicalHistory);
    MedicalHistory edit(MedicalHistory medicalHistory);
    MedicalHistory remove(MedicalHistory medicalHistory);
    List<MedicalHistory> findAll();
    MedicalHistory findById(Long id);

}
