package com.home.SpringBootAutomation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Organisation;

public interface OrganisationService {

    Organisation save(Organisation organisation);

    Organisation update(Organisation organisation) throws NoContentException;

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Organisation> findAll();

    Optional<Organisation> findById(Long id) throws NoContentException;

    Optional<Organisation> findOrganisationByIdAndDeletedFalse(Long id) throws NoContentException;

    List<Organisation> findOrganisationByDeletedFalse() throws NoContentException;

}
