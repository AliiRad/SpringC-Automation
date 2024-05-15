package com.home.SpringBootAutomation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Section;

public interface SectionService {

    Section save(Section section);

    Section edit(Section section) throws NoContentException;

    Section update(Section section) throws NoContentException;

    Section findById(Long id) throws NoContentException;

    @Transactional
    Section logicalRemove(Long id) throws NoContentException;

    List<Section> findAll();

    List<Section> findSectionByDeletedFalse() throws NoContentException;
    
}
