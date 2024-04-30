package com.home.SpringBootAutomation.controller;


import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.model.Ticket;
import com.home.SpringBootAutomation.service.impl.TicketServiceImp;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/ticket")
public class TicketController {
    private TicketServiceImp ticketServiceImp;

    public TicketController(TicketServiceImp ticketServiceImp) {
        this.ticketServiceImp = ticketServiceImp;
    }

    @PostMapping(value = "/save")
    public String saveTicket( Ticket ticket, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                return "ticket";
            } else {
                log.info("Controller-Ticket-Post-Save: " + ticket.toString());
                ticketServiceImp.save(ticket);
                model.addAttribute("messageType", "success");
                model.addAttribute("messageContent", "Ticket Saved successfully");
                return "redirect:/ticket";
            }
        } catch (Exception e) {
            log.error(e.getMessage());
//            e.printStackTrace();
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/ticket";
        }
    }

    @PutMapping(value = "/edit")
    public String editTicket(Ticket ticket, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                throw new ValidationException(result.getAllErrors().toString());
            }
            log.info("Controller-Ticket-Put-Edit: " + ticket);
            ticketServiceImp.edit(ticket);
            model.addAttribute("ticket", ticket);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Ticket Edited Successfully .");
            return "redirect:/ticket";
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return null;
        }

    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteTicket(@PathVariable Long id, Model model) {
        try {
            log.info("Controller-Ticket-Delete-Delete: " + id);
            ticketServiceImp.logicalRemove(id);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Ticket Deleted Successfully .");
            return "redirect:/ticket";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return null;
        }
    }

    @GetMapping
    public String showTickets(Model model) {
        try {
            log.info("Controller-Ticket-Get-FindAll");
            model.addAttribute("ticket", new Ticket());
            model.addAttribute("ticketList", ticketServiceImp.findAll());
            return "ticket";
//            if (!ticketServiceImp.findAll().isEmpty()) {
//                log.info("Controller-Ticket-Get-FindAll");
//                model.addAttribute("ticket", new Ticket());
//                model.addAttribute("ticketList", ticketServiceImp.findAll());
//                return "ticket";
//            } else {
//                model.addAttribute("messageType", "error");
//                model.addAttribute("messageContent", "Ticket List Is Empty");
//                return "ticket";
//            }
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return null;
        }
    }


    @GetMapping(value = "/id/{id}")
    @ResponseBody
    public Ticket showTicket(@PathVariable("id") Long id, Model model) {
        try {
            Ticket ticket = ticketServiceImp.findById(id);
            if (ticket != null) {
                log.info("Controller-Ticket-Get-FindById-Ticket: " + ticket);
                model.addAttribute("messageType", "success");
                model.addAttribute("messageContent", "Active Ticket With Id : " + id + " Was Found");
                return ticket;
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

//    @GetMapping(value = "/applicant")
//    public String showTicketsByApplicant(Model model, @ModelAttribute("applicant") Person applicant) {
//        try {
//            List<Ticket> ticketList = ticketServiceImp.findByApplicant(applicant);
//            if (!ticketList.isEmpty()) {
//                log.info("Controller-Ticket-Get-FindByApplicant: " + ticketList);
//                model.addAttribute("messageType", "success");
//                return "ticket";
//            } else {
//                model.addAttribute("messageType", "error");
//                model.addAttribute("messageContent", "Ticket List Is Empty");
//                //todo: return error page
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            model.addAttribute("messageType", "error");
//            model.addAttribute("messageContent", e.getMessage());
//            //todo: return error page
//            return null;
//        }
//    }

    @GetMapping(value = "/date")
    public String showTicketsByTimeStamp(Model model, @ModelAttribute("date") LocalDateTime timeStamp) {

        try {
            log.info("Controller-Ticket-Get-FindByDate");
            List<Ticket> ticketList = ticketServiceImp.findByDate(timeStamp);
            if (!ticketList.isEmpty()) {
                log.info("Controller-Ticket-Get-FindByDate-Ticket: " + ticketList);
                model.addAttribute("messageType", "success");
                return "ticket";
            } else {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", "Active Ticket With Date : " + timeStamp + " Was Not Found");
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


}

