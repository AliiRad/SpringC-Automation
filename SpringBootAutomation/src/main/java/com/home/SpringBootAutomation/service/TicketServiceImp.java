package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.model.Ticket;
import com.home.SpringBootAutomation.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TicketServiceImp implements TicketService {
    private TicketRepository ticketRepository;

    public TicketServiceImp(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket save(Ticket ticket) {
        log.info("Service-Ticket-Save");
        ticket.setActive(true);
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    public Ticket edit(Ticket ticket) {
        log.info("Service-Ticket-Edit");
        ticket.setActive(true);
        if (findById(ticket.getId()) != null) {
            ticketRepository.save(ticket);
            return ticket;
        } else return null;
    }

    @Override
    public Ticket remove(Ticket ticket) {
        log.info("Service-Ticket-Remove");
        if (findById(ticket.getId()) != null) {
            ticketRepository.delete(ticket);
            return ticket;
        } else return null;
    }

    @Override
    public Ticket logicalRemove(Long id) {
        log.info("Service-Ticket-LogicalRemove");
        Ticket ticket = findById(id);
        if (ticket != null) {
            ticketRepository.logicalDelete(id);
            return ticket;
        } else return null;
    }

    @Override
    public List<Ticket> findAll() {
        log.info("Service-Ticket-FindAll");
        List<Ticket> ticketList = ticketRepository.findAll();
        return ticketList;
    }

    @Override
    public Ticket findById(Long id) {
        log.info("Service-Ticket-FindById");
        Optional<Ticket> ticket = ticketRepository.findById(id);
        return (ticket.isPresent() ? ticket.get() : null);
    }

    @Override
    public List<Ticket> findByApplicant(String applicant) {
        log.info("Service-Ticket-FindByApplicant");
        List<Ticket> ticketList = ticketRepository.findByApplicant(applicant);
        return ticketList;
    }

    @Override
    public List<Ticket> findByDate(LocalDate localDate) {
        log.info("Service-Ticket-FindByDate");
        List<Ticket> ticketList = ticketRepository.findByDate(localDate);
        return ticketList;
    }
}
