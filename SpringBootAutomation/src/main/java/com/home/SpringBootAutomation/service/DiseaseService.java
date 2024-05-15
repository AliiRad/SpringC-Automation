package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Disease;

import java.util.List;

public interface DiseaseService {
    Disease save(Disease disease) throws NoContentException;

    Disease edit(Disease disease) throws NoContentException;

    void remove(Disease disease) throws NoContentException;

    Disease logicalRemove(Long id) throws NoContentException;

    List<Disease> findAll();

    List<Disease> findAllDeletedFalse();

    Disease findById(Long id) throws NoContentException;

    List<Disease> findDiseaseByName(String name);

    List<Disease> findDiseaseByType(String type) ;
}
