package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Response;
import com.home.SpringBootAutomation.model.Ticket;
import com.home.SpringBootAutomation.model.TicketGroup;
import com.home.SpringBootAutomation.service.impl.TicketGroupServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value ="/ticketGroup")
public class TicketGroupController {
    private TicketGroupServiceImp ticketGroupServiceImp;

    public TicketGroupController(TicketGroupServiceImp ticketGroupServiceImp) {
        this.ticketGroupServiceImp = ticketGroupServiceImp;
    }
    @PostMapping(value = "/save")
    @ResponseBody
    public TicketGroup saveTicket(TicketGroup ticketGroup) {
        log.info("Controller-TicketGroup-Post-Save: " + ticketGroup.toString());
        log.info("Controller-TicketGroup-Post-Save");
        ticketGroupServiceImp.save(ticketGroup);
        return  ticketGroupServiceImp.save(ticketGroup);
    }

    @PutMapping(value ="/edit")
    @ResponseBody
    public TicketGroup editTicket(TicketGroup ticketGroup) {
        log.info("Controller-TicketGroup-Post-Edit");
        return ticketGroupServiceImp.edit(ticketGroup);
    }

    @DeleteMapping(value ="/delete/{id}")
    @ResponseBody
    public TicketGroup deleteTicket(@PathVariable Long id ) {
        TicketGroup ticketGroup = ticketGroupServiceImp.findById(id);
        log.info("Controller-Ticket-Post-Delete: " + ticketGroup);
        return ticketGroupServiceImp.remove(ticketGroup);
    }

    @GetMapping
    public String showTicketGroups(Model model) {
        log.info("Controller-TicketGroup-Get-FindAll");
        model.addAttribute("ticketGroup", new TicketGroup());
        model.addAttribute("ticketGroupList", ticketGroupServiceImp.findAll());
        model.addAttribute("ticketGroupParents", ticketGroupServiceImp.findByParentRoot());
        return "ticketGroup";
    }

    @GetMapping(value = "/id/{id}")
    @ResponseBody
    public TicketGroup showTicket(@PathVariable("id") Long id, Model model) {
        try {
            TicketGroup ticketGroup = ticketGroupServiceImp.findById(id);
            if (ticketGroup != null) {
                log.info("Controller-Ticket-Get-FindById-Ticket: " + ticketGroup);
                model.addAttribute("messageType", "success");
                model.addAttribute("messageContent", "Active Ticket With Id : " + id + " Was Found");
                return ticketGroup;
            } else {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", "Active Ticket With Id : " + id + " Was Not Found");
                //todo: return error page
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            //todo: return error page
            return null;
        }
    }


    @GetMapping(value = "/parent/{id}")
    @ResponseBody
    public List<TicketGroup> showTickets(@PathVariable("id") Long id) {
        try {
            List<TicketGroup> ticketGroups = ticketGroupServiceImp.findByParentId(id);
            if (!ticketGroups.isEmpty()) {
                log.info("Controller-TicketGroup-Get-FindByParentId-TicketGroup: " + ticketGroups);
                return ticketGroups;
            } else {
                //todo: return error page
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //todo: return error page
            return null;
        }
    }


}
