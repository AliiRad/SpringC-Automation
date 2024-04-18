package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.PersonModel;
import com.home.SpringBootAutomation.model.Ticket;
import com.home.SpringBootAutomation.service.impl.TicketServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value ="/ticket")
public class TicketController {
    private TicketServiceImp ticketServiceImp;

    public TicketController(TicketServiceImp ticketServiceImp) {
        this.ticketServiceImp = ticketServiceImp;
    }

    @GetMapping
    public String showTickets(Model model) {
        log.info("Controller-Ticket-Get-FindAll");
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("ticketList", ticketServiceImp.findAll());
        return "ticket";
    }

    @GetMapping(value ="/id/{id}")
    public String showTicket(@PathVariable("id") Long id , Model model){
        log.info("Controller-Ticket-Get-FindById");
        Ticket ticket = ticketServiceImp.findById(id);
        if (ticket != null){
            model.addAttribute("ticket", ticket);
            return "ticket";
        }else {
            return "redirect:/ticket";
        }
    }

    @GetMapping(value ="/applicant")
    public String showTicketsByApplicant(Model model , @ModelAttribute("applicant") PersonModel applicant) {
        log.info("Controller-Ticket-Get-FindByApplicant");
        List<Ticket> ticketList = ticketServiceImp.findByApplicant(applicant);
        if (!ticketList.isEmpty()){
            model.addAttribute("ticketList", ticketList);
            return "ticket";
        }else {
            return "error-404";
        }
    }

    @GetMapping(value ="/date")
    public String showTicketsByTimeStamp(Model model , @ModelAttribute("date") LocalDateTime timeStamp) {
        log.info("Controller-Ticket-Get-FindByDate");
        List<Ticket> ticketList = ticketServiceImp.findByDate(timeStamp);
        if (!ticketList.isEmpty()){
            model.addAttribute("ticketList", ticketList);
            return "ticket";
        }else {
            return "error-404";
        }
    }

    @PostMapping(value = "/save")
    public String saveTicket(Ticket ticket) {
        log.info("Controller-Ticket-Post-Save: " + ticket.toString());
        log.info("Controller-Ticket-Post-Save");
        ticketServiceImp.save(ticket);
        return "redirect:/ticket";
    }

    @PostMapping(value ="/edit")
    public String editTicket(Ticket ticket) {
        log.info("Controller-Ticket-Post-Edit");
        ticketServiceImp.edit(ticket);
        return "ticket";
    }

    @PostMapping(value ="/delete")
    public String deleteTicket(Long id) {
        log.info("Controller-Ticket-Post-Delete: " + id);
        ticketServiceImp.logicalRemove(id);
        return "redirect:/ticket";
    }


}

