package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Ticket;
import com.home.SpringBootAutomation.service.TicketServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
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

    @GetMapping(value ="/id")
    public String showTicket(@ModelAttribute("id") Long id){
        log.info("Controller-Ticket-Get-FindById");
        Ticket ticket = ticketServiceImp.findById(id);
        if (ticket != null){
            return "ticket";
        }else {
            return "error-404";
        }
    }

    @GetMapping(value ="/applicant")
    public String showTicketsByApplicant(Model model , @ModelAttribute("applicant") String applicant) {
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
    public String showTicketsByApplicant(Model model , @ModelAttribute("date") LocalDate date) {
        log.info("Controller-Ticket-Get-FindByDate");
        List<Ticket> ticketList = ticketServiceImp.findByDate(date);
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
    public String deleteTicket(Ticket ticket) {
        log.info("Controller-Ticket-Post-Delete");
        ticketServiceImp.logicalRemove(ticket.getId());
        return "ticket";
    }


}
