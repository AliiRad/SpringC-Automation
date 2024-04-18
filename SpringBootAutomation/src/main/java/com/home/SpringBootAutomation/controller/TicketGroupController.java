package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.TicketGroup;
import com.home.SpringBootAutomation.service.impl.TicketGroupServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value ="/ticketGroup")
public class TicketGroupController {
    private TicketGroupServiceImp ticketGroupServiceImp;

    public TicketGroupController(TicketGroupServiceImp ticketGroupServiceImp) {
        this.ticketGroupServiceImp = ticketGroupServiceImp;
    }


    @GetMapping
    public String showTicketGroups(Model model) {
        log.info("Controller-TicketGroup-Get-FindAll");
        model.addAttribute("ticketGroup", new TicketGroup());
        model.addAttribute("ticketGroupList", ticketGroupServiceImp.findAll());
        return "ticket";
    }

    @PostMapping(value = "/save")
    public String saveTicket(TicketGroup ticketGroup) {
        log.info("Controller-TicketGroup-Post-Save: " + ticketGroup.toString());
        log.info("Controller-TicketGroup-Post-Save");
        ticketGroupServiceImp.save(ticketGroup);
        return "redirect:/ticket";
    }

    @PostMapping(value ="/edit")
    public String editTicket(TicketGroup ticketGroup) {
        log.info("Controller-TicketGroup-Post-Edit");
        ticketGroupServiceImp.edit(ticketGroup);
        return "ticket";
    }

    @PostMapping(value ="/delete")
    public String deleteTicket(TicketGroup ticketGroup) {
        log.info("Controller-Ticket-Post-Delete: " + ticketGroup);
        ticketGroupServiceImp.remove(ticketGroup);
        return "redirect:/ticket";
    }
}
