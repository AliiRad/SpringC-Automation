package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.model.Ticket;
import com.home.SpringBootAutomation.service.impl.OrganisationServiceImp;
import com.home.SpringBootAutomation.service.impl.TicketGroupServiceImp;
import com.home.SpringBootAutomation.service.impl.TicketServiceImp;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Slf4j
@Controller
@RequestMapping("/organisation")
public class OrganisationController {
    
    private OrganisationServiceImp service;

        public OrganisationController(OrganisationServiceImp service) {
        this.service = service;
    }

    @GetMapping
    public String findall(Model model) {

        log.info("Controller-Organisation-GetMapping");
        return "organisation";
    }
    

}
