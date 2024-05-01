package com.home.SpringBootAutomation.service.impl;

import java.util.List;
import java.util.Optional;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Section;
import com.home.SpringBootAutomation.repository.SectionRepository;
import com.home.SpringBootAutomation.service.SectionService;

/*
 * Will Finish These tommorow 
 */

public class SectionServiceImp implements SectionService{

    private final SectionRepository repository;

    public SectionServiceImp(SectionRepository repository) {
        this.repository = repository;
   }
    @Override
    public List<Section> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Section> findById(Long id) throws NoContentException {
        return Optional.empty();
    }

    @Override
    public List<Section> findSectionByDeletedFalse() throws NoContentException {
        return null;
    }

    @Override
    public Optional<Section> findSectionByIdAndDeletedFalse() throws NoContentException {
        return Optional.empty();
    }

    @Override
    public void logicalRemove(Long id) throws NoContentException {
        
    }

    @Override
    public Section save(Section section) {
        return repository.save(section);
    }

    @Override
    public Section update(Section section) {
        return null;
    }
    
}
