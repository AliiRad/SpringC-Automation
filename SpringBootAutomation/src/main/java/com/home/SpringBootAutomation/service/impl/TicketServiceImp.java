package com.home.SpringBootAutomation.service.impl;


import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.model.Ticket;
import com.home.SpringBootAutomation.repository.PersonRepository;
import com.home.SpringBootAutomation.repository.TicketRepository;
import com.home.SpringBootAutomation.service.PersonService;
import com.home.SpringBootAutomation.service.TicketService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TicketServiceImp implements TicketService {
    private final TicketRepository ticketRepository;
    private final PersonService personService;

    public TicketServiceImp(TicketRepository ticketRepository, PersonService personService) {
        this.ticketRepository = ticketRepository;
        this.personService = personService;
    }

    @Override
    public Ticket save(Ticket ticket) throws NoContentException {
        log.info("Service-Ticket-Save");
//        Optional<Person> person = personService.findPersonByUserNameAndDeletedFalse(Principal.class.getName());
//        ticket.setApplicant(person.get());
        ticket.setDeleted(true);
        ticket.setTicketTimeStamp(LocalDateTime.now());
        log.info(ticket.toString());
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    public Ticket edit(Ticket ticket) throws NoContentException {
        log.info("Service-Ticket-Edit");
        ticket.setDeleted(true);
        if (findById(ticket.getId()) != null) {
            ticketRepository.save(ticket);
            return ticket;
        } else throw new NoContentException("Ticket Not Found !");
    }

    @Override
    public Ticket remove(Ticket ticket) throws NoContentException {
        log.info("Service-Ticket-Remove");
        if (findById(ticket.getId()) != null) {
            ticketRepository.delete(ticket);
            return ticket;
        } else throw new NoContentException("Ticket Not Found !");
    }

    @Override
    @Transactional
    public Ticket logicalRemove(Long id) throws NoContentException {
        log.info("Service-Ticket-LogicalRemove");
        Ticket ticket = findById(id);
        log.info("Service-Ticket-LogicalRemove: " + ticket);
        if (ticket != null) {
            ticket.setDeleted(false);
            ticketRepository.save(ticket);
            return ticket;
        } else throw new NoContentException("Ticket Not Found !");
    }

    @Override
    public List<Ticket> findAll() {
        log.info("Service-Ticket-FindAll");
        List<Ticket> ticketList = ticketRepository.findAll();
        return ticketList;
    }

    @Override
    public List<Ticket> findAllDeletedFalse() {
        log.info("Service-Ticket-FindAllDeletedFalse");
        List<Ticket> ticketList = ticketRepository.findAllDeletedFalse();
        return ticketList;
    }

    @Override
    public Ticket findById(Long id) throws NoContentException {
        log.info("Service-Ticket-FindById");
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            log.info("Service-Ticket-FindById: " + ticket.get());
            return ticket.get();
        } else throw new NoContentException("Ticket Not Found !");
    }


//    @Override
//    public List<Ticket> findByApplicant(Person applicant) throws NoContentException {
//        log.info("Service-Ticket-FindByApplicant");
//        List<Ticket> ticketList = ticketRepository.findByApplicant(applicant);
//        if (!ticketList.isEmpty()){
//            return ticketList;
//        }else throw new NoContentException("Ticket Not Found !");
//    }

    @Override
    public List<Ticket> findByDate(LocalDateTime timeStamp) throws NoContentException {
        log.info("Service-Ticket-FindByDate");
        List<Ticket> ticketList = ticketRepository.findByDate(timeStamp);
        if (!ticketList.isEmpty()) {
            return ticketList;
        } else throw new NoContentException("Ticket Not Found !");
    }
}
