package com.home.SpringBootAutomation.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Organisation;
import com.home.SpringBootAutomation.repository.OrganisationRepository;
import com.home.SpringBootAutomation.service.OrganisationService;

@Service
public class OrganisationServiceImp implements OrganisationService {

    private final OrganisationRepository repository;

    public OrganisationServiceImp(OrganisationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Organisation> findAll() {
        return repository.findAll();
    }

    @Override
    public Organisation findById(Long id) throws NoContentException {
        return repository.findById(id).orElseThrow(
        () -> new NoContentException("Organisation not found !" + id)
        );
        
        // Optional<Organisation> optional = repository.findById(id);
        // if (optional.isPresent()) {
        //     return optional;
        // } else {
        //     throw new NoContentException("Organisation not found !");
        // }
    }

    @Override
    public Organisation edit(Organisation organisation) throws NoContentException {
        repository.findById(organisation.getId()).orElseThrow(
            () -> new NoContentException("No Organisation Found with id : " + organisation.getId())
        );
        return repository.save(organisation);
    }

    @Override
    public List<Organisation> findOrganisationByDeletedFalse() throws NoContentException {
        return repository.findOrganisationByDeletedFalse();
    }

    @Transactional
    @Override
    public Organisation logicalRemove(Long id) throws NoContentException {

        Organisation organisation = repository.findById(id).orElseThrow(
            () -> new NoContentException("No Organisation Found With Id : " + id)
            );
        return repository.save(organisation);
        

        // Optional<Organisation> optionalSection = repository.findOrganisationByIdAndDeletedFalse(id);
        // if (optionalSection.isPresent()) {
        //     repository.logicalRemove(id);
        // } else {
        //     throw new NoContentException("Organisation not found !");
        // }

    }

    @Override
    public Organisation save(Organisation organisation) {
        return repository.save(organisation);
    }

    @Override
    public Organisation update(Organisation organisation) throws NoContentException {
        Optional<Organisation> optional = repository.findOrganisationByIdAndDeletedFalse(organisation.getId());

        if (optional.isPresent()) {
            return repository.save(organisation);
        } else {
            throw new NoContentException("Organisation not found !");
        }
    }

}
