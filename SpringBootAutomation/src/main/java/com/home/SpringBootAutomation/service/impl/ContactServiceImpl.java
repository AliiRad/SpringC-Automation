package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.enums.Status;
import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Contact;
import com.home.SpringBootAutomation.repository.ContactRepository;
import com.home.SpringBootAutomation.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
public abstract class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository){
        this.contactRepository = contactRepository;

    }

    @Override
    public Contact save(Contact contact) {
        log.info("Service-Contact-Save");

        contact.setStatus(Status.postponed);
        contact.setDeleted(false);
        contact.setTicketTimeStamp(LocalDateTime.now());
        log.info(contact.toString());
        contactRepository.save(contact);
        return contact;}

    @Override
    @Transactional
    public Contact edit(Contact contact) throws NoContentException {
        log.info("Service-Contact-Edit");
        contactRepository.findById(contact.getId()).orElseThrow(
                () -> new NoContentException("No Contact with id : " + contact.getId())
        );
        return contactRepository.save(contact);
    }

    @Transactional
    @Override
    public void remove(Contact contact) throws NoContentException {
        log.info("Service-Contact-Remove");
        contactRepository.findById(contact.getId()).orElseThrow(
                () -> new NoContentException("No Contact with id : " + contact.getId())
        );
        contactRepository.delete(contact);
    }

    @Override
    @Transactional
    public Contact logicalRemove(Long id) throws NoContentException {
        log.info("Service-Contact-LogicalRemove");
        Contact contact = contactRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Contact found with id : " + id));
        contact.setDeleted(true);
        return contactRepository.save(contact);
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
                () -> new NoContentException("No Contact found with id " + id)
        );
    }
}