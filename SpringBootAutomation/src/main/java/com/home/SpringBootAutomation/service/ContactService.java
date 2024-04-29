package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Contact;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ContactService {

    Contact save(Contact contact);

    Contact update(Contact contact) throws NoContentException;

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Contact> findAll();

    Optional<Contact> findById(Long id) throws NoContentException;

    Long getContactCount();

    Contact logicalRemoveWithReturn(Long id) throws NoContentException;

    List<Contact> findContactByDeletedFalse();
    Optional<Contact> findContactByIdAndDeletedFalse(Long id) throws NoContentException;

    Long countByDeletedFalse();
}
