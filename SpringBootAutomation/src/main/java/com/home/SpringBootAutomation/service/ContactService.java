package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Contact;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface ContactService {

    Contact save(Contact contact);
    Contact edit(Contact contact) throws NoContentException;
    Contact remove(Long id) throws NoContentException;

    @Transactional
    Contact logicalRemove(Long id) throws NoContentException;


    List<Contact> findContactByDeletedFalse();
    List<Contact> findAll();
    Contact findById(Long id) throws NoContentException;

}
