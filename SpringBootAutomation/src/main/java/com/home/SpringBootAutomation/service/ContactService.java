package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Contact;
import com.home.SpringBootAutomation.repository.ContactRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public abstract class ContactService {
    private ContactRepository contactRepository;

    public  ContactService(ContactRepository contactRepository) { this.contactRepository = contactRepository; }

    public Contact save(Contact contact) { return contactRepository.save(contact); }

    public Contact edit(@Valid Contact contact) throws NoContentException {
        contactRepository.findById(contact.getId()).orElseThrow(
                () -> new NoContentException("No Contact Found with id : " + contact.getId())
        );
        return contactRepository.save(contact);
    }

    public Contact remove(Long id) throws NoContentException {
        Contact contact = contactRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Contact Found with id : " + id)
        );
        contactRepository.deleteById(id);
        return contact;
    }

    @Transactional
    public abstract Contact logicalRemove(Long id) throws NoContentException;

    public List<Contact> findAll() { return contactRepository.findAll(); }

    public abstract List<Contact> findAllDeletedFalse();

    public Contact findById(Long id) throws NoContentException {
        return contactRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Contact Found with id : " + id)
        );
    }
}
