package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.model.Disease;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiseaseService {
    Disease save (Disease disease);
    Disease edit (Disease disease);
    Disease remove (Disease disease);
    List<Disease> findAll() ;
    Disease findById(Long id);
}
