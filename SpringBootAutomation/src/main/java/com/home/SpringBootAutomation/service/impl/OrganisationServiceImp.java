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
    public Optional<Organisation> findById(Long id) throws NoContentException {
        repository.findById(id).orElseThrow(
        () -> new NoContentException("Organisation not found !" + id)
        );
        return repository.findById(id);
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

    @Override
    public Optional<Organisation> findOrganisationByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Organisation> optional = repository.findOrganisationByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("Organisation not found !");
        }
    }


    @Transactional
    @Override
    public void logicalRemove(Long id) throws NoContentException {
        Optional<Organisation> optionalSection = repository.findOrganisationByIdAndDeletedFalse(id);
        if (optionalSection.isPresent()) {
            repository.logicalRemove(id);
        } else {
            throw new NoContentException("Organisation not found !");
        }

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
