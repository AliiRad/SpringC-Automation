package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.MedicalHistory;
import com.home.SpringBootAutomation.model.Ticket;

import javax.naming.NameNotFoundException;
import java.util.List;

public interface MedicalHistoryService {
    MedicalHistory save(MedicalHistory medicalHistory) throws NoContentException;
    MedicalHistory edit(MedicalHistory medicalHistory)throws NoContentException;
    MedicalHistory remove(MedicalHistory medicalHistory)throws NoContentException;
    MedicalHistory logicalRemove(Long id) throws NoContentException;
    List<MedicalHistory> findAll();

    List<MedicalHistory> findAllDeletedFalse();

    MedicalHistory findById(Long id)throws NoContentException;

}
