package com.home.SpringBootAutomation.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Section;
import com.home.SpringBootAutomation.repository.SectionRepository;
import com.home.SpringBootAutomation.service.SectionService;

@Service
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
    public Section findById(Long id) throws NoContentException {
        return repository.findById(id).orElseThrow(
            () -> new NoContentException("No Section Found with id : " + id)
        );
    }

    @Override
    public Section edit(Section section) throws NoContentException {
        repository.findById(section.getId()).orElseThrow(
            () -> new NoContentException("No Section Found with id : " + section.getId())
        );
        return repository.save(section);
    }
    
    @Override
    public Section save(Section section) {
        return repository.save(section);
    }

    @Override
    public Section update(Section section) throws NoContentException {
        Optional<Section> optional = repository.findSectionByIdAndDeletedFalse(section.getId());

        if (optional.isPresent()) {
            return repository.save(section);
        } else {
            throw new NoContentException("Section not found !");
        }
    }

    @Transactional
    @Override
    public Section logicalRemove(Long id) throws NoContentException {

        Section section = repository.findById(id).orElseThrow(
            () -> new NoContentException("No Section Found With Id : " + id));
        return repository.save(section);

        // Optional<Section> optionalSection = repository.findSectionByIdAndDeletedFalse(id);
        // if (optionalSection.isPresent()) {
        //     repository.logicalRemove(id);
        // } else {
        //     throw new NoContentException("Section not found !");
        // }
    }

    @Override
    public List<Section> findSectionByDeletedFalse() throws NoContentException {
        return repository.findSectionByDeletedFalse();
    }


    
}
