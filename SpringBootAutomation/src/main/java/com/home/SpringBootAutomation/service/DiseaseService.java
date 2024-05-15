package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Disease;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.lang.model.element.Name;
import java.lang.reflect.Type;
import java.util.List;

public interface DiseaseService {
    Disease save(Disease disease) throws NoContentException;

    Disease edit(Disease disease) throws NoContentException;

    void remove(Disease disease) throws NoContentException;

    Disease logicalRemove(Long id) throws NoContentException;

    List<Disease> findAll();

    List<Disease> findAllDeletedFalse();

    Disease findById(Long id) throws NoContentException;

    List<Disease> findDiseaseByName(Name name) throws NoContentException;

    List<Disease> findDiseaseByType(Type type) throws NoContentException;
}
