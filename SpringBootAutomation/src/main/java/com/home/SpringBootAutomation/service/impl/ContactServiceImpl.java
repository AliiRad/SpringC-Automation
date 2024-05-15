package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Contact;
import com.home.SpringBootAutomation.repository.ContactRepository;
import com.home.SpringBootAutomation.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository){
        this.contactRepository = contactRepository;

    }

    @Override
    public Contact save(Contact contact) {
        log.info("Service-Contact-Save");
        return contactRepository.save(contact);}

    @Override
    public Contact edit(Contact contact) throws NoContentException {
        log.info("Service-Contact-Edit");
        contactRepository.findById(contact.getId()).orElseThrow(
                () -> new NoContentException("No Contact with id : " + contact.getId())
        );
        return contactRepository.save(contact);
    }

    @Override
    public Contact remove(Long id) throws NoContentException {
        log.info("Service-Contact-Remove");
        Contact contact = contactRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Contact with id : " + id)
        );
        contactRepository.deleteById(id);
        return contact;
    }

    @Override
    @Transactional
    public Contact logicalRemove(Long id) throws NoContentException {
        log.info("Service-Contact-LogicalRemove");
        Contact contact = contactRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Contact found with id : " + id));
        contactRepository.logicalRemove(id);
        return contact;
    }

    @Override
    public List<Contact> findAll() {
        log.info("Service-Contact-FindAll");
        List<Contact> contactList = contactRepository.findAll();
        return contactList;
    }

    @Override
    public Contact findById(Long id) throws NoContentException {
        log.info("Service-Contact-FindById");
        return contactRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Contact found with id : " + id)
        );
    }

    @Override
    public List<Contact> findContactByDeletedFalse() {
        return contactRepository.findContactByDeletedFalse();
    }

}