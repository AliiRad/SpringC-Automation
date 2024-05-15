package com.home.SpringBootAutomation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Organisation;

public interface OrganisationService {

    Organisation save(Organisation organisation);

    Organisation edit(Organisation organisation) throws NoContentException;

    Organisation update(Organisation organisation) throws NoContentException;

    Organisation findById(Long id) throws NoContentException;

    @Transactional
    Organisation logicalRemove(Long id) throws NoContentException;

    List<Organisation> findAll();

    List<Organisation> findOrganisationByDeletedFalse() throws NoContentException;

}
