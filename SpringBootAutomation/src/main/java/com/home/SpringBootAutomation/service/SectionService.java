package com.home.SpringBootAutomation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Section;

public interface SectionService {

    Section save(Section section);

    Section update(Section section);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Section> findAll();

    Optional<Section> findById(Long id) throws NoContentException;

    Optional<Section> findSectionByIdAndDeletedFalse() throws NoContentException;

    List<Section> findSectionByDeletedFalse() throws NoContentException;
    
}
