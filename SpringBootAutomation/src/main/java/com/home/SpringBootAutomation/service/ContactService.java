package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Contact;
import com.home.SpringBootAutomation.repository.ContactRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface ContactService {

    // ContactRepository contactRepository;

    // public ContactService(ContactRepository contactRepository) { 
    //     this.contactRepository = contactRepository; 
    // }

    public Contact save(Contact contact);

    public Contact edit(@Valid Contact contact) throws NoContentException;

    public Contact remove(Contact contact) throws NoContentException;

    @Transactional
    public abstract Contact logicalRemove(Long id) throws NoContentException;

    public List<Contact> findAll();

    public abstract List<Contact> findAllDeletedFalse();

    public Contact findById(Long id) throws NoContentException;
}
