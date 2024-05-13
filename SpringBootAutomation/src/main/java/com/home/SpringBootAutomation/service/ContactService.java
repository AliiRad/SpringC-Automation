package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Contact;
import com.home.SpringBootAutomation.repository.ContactRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private ContactRepository contactRepository;
    private Contact contact;

    public  ContactService(ContactRepository contactRepository) { this.contactRepository = contactRepository; }

    public Contact save(Contact contact) { return contactRepository.save(contact); }

    public Contact edit(@Valid Contact person) throws NoContentException {
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

    public List<Contact> findAll() { return contactRepository.findAll(); }

    public Contact findById(Long id) throws NoContentException {
        return contactRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Contact Found with id : " + id)
        );
    }
}
