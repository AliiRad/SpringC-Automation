package com.home.SpringBootAutomation.service.impl;

import java.util.List;
import java.util.Optional;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Organisation;
import com.home.SpringBootAutomation.repository.OrganisationRepository;
import com.home.SpringBootAutomation.service.OrganisationService;

/*
 * Will Finish These tommorow 
 */

public class OrganisationServiceImp implements OrganisationService{

    private final OrganisationRepository repository;

    public OrganisationServiceImp(OrganisationRepository repository) {
        this.repository = repository;
   }

    @Override
    public List<Organisation> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Organisation> findById(Long id) throws NoContentException {
        return Optional.empty();
    }

    @Override
    public List<Organisation> findSectionByDeletedFalse() throws NoContentException {
        return null;
    }

    @Override
    public Optional<Organisation> findSectionByIdAndDeletedFalse() throws NoContentException {
        return Optional.empty();
    }

    @Override
    public void logicalRemove(Long id) throws NoContentException {
        
    }

    @Override
    public Organisation save(Organisation organisation) {
        return repository.save(organisation);
    }

    @Override
    public Organisation update(Organisation organisation) {
        return null;
    }
    
}
