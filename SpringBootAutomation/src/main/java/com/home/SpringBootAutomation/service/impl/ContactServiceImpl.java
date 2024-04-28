package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Contact;
import com.home.SpringBootAutomation.repository.ContactRepository;
import com.home.SpringBootAutomation.service.ContactService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;

    public ContactServiceImpl(ContactRepository repository){ this.repository = repository;}

    @Override
    public Contact save(Contact contact) { return repository.save(contact); }

    @Override
    public Contact update(Contact contact) throws NoContentException {
        Optional<Contact> optionalContact = repository.findContactByIdAndDeletedFalse(contact.getId());

        if(optionalContact.isPresent()){
            return repository.save(contact);
        } else {
            throw new NoContentException("Contact not found !");
        }
    }

    @Transactional
    @Override
    public void logicalRemove(Long id) throws NoContentException {
        Optional<Contact> optionalContact = repository.findContactByIdAndDeletedFalse(id);
        if (optionalContact.isPresent()) {
            repository.logicalRemove(id);
        } else {
            throw new NoContentException("Contact not found  !");
        }
    }

    @Override
    public List<Contact> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Contact> findById(Long id) throws NoContentException {
        Optional<Contact> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("Contact not found  !");
        }
    }

    @Override
    public Long getContactCount() { return repository.count(); }

    @Override
    public Contact logicalRemoveWithReturn(Long id) throws NoContentException {
        Optional<Contact> optionalContact = repository.findContactByIdAndDeletedFalse(id);

        if (optionalContact.isPresent()) {
            Contact oldContact = optionalContact.get();
            oldContact.setDeleted(true);
            return repository.save(oldContact);

        } else {
            throw new NoContentException("Contact not found  !");
        }

    }

    @Override
    public List<Contact> findContactByDeletedFalse() {
        return repository.findContactByDeletedFalse();
    }

    @Override
    public Optional<Contact> findContactByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Contact> optional = repository.findContactByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("Contact not found  !");
        }
    }

    @Override
    public Long countByDeletedFalse() { return repository.countByDeletedFalse(); }
}
