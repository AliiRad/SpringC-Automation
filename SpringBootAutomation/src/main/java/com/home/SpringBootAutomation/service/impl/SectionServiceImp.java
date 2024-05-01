package com.home.SpringBootAutomation.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Section;
import com.home.SpringBootAutomation.repository.SectionRepository;
import com.home.SpringBootAutomation.service.SectionService;

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
        Optional<Section> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("Section not found !");
        }
    }

    @Override
    public List<Section> findSectionByDeletedFalse() throws NoContentException {
        return null;
    }

    @Override
    public Optional<Section> findSectionByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Section> optionalSection = repository.findSectionByIdAndDeletedFalse(id);
        if (optionalSection.isPresent()) {
            return optionalSection;
        } else {
            throw new NoContentException("Section not found !");
        }
    }

    @Transactional
    @Override
    public void logicalRemove(Long id) throws NoContentException {
        Optional<Section> optionalSection = repository.findSectionByIdAndDeletedFalse(id);
        if (optionalSection.isPresent()) {
            repository.logicalRemove(id);
        } else {
            throw new NoContentException("Section not found !");
        }
    }

    @Override
    public Section save(Section section) {
        return repository.save(section);
    }

    @Override
    public Section update(Section section) throws NoContentException {
        Optional<Section> optionalPerson = repository.findPersonByIdAndDeletedFalse(section.getId());

        if (optionalPerson.isPresent()) {
            return repository.save(section);
        } else {
            throw new NoContentException("Section not found !");
        }
    }
    
}
