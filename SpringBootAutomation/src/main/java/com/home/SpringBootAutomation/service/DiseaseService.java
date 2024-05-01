package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Disease;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiseaseService {
    Disease save (Disease disease) throws NoContentException;
    Disease edit (Disease disease)throws NoContentException;
    Disease remove (Disease disease)throws NoContentException;
    Disease logicalRemove (Disease disease)throws NoContentException;

    Disease logicalRemove(Long id) throws NoContentException;

    List<Disease> findAll() ;

    List<Disease> findAllDeletedFalse();

    Disease findById(Long id)throws NoContentException;
}
