package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Organisation;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.service.impl.OrganisationServiceImp;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



@Slf4j
@Controller
@RequestMapping("/organisation")
public class OrganisationController {
    
    private OrganisationServiceImp service;

        public OrganisationController(OrganisationServiceImp service) {
        this.service = service;
    }

    @GetMapping
    public String showOrganisation(Model model) {
        // log.info("Controller-Organisation-GetMapping");
        // model.addAttribute("organisation List",service.findAll());
        // model.addAttribute("organisation",new Organisation());
        // return "organisation";

        //TODO: Add Model Organisation
        return service.findAll().toString();
    }

    @PostMapping
    @ResponseBody
    public Organisation saveOrganisation(@Valid Organisation organisation, BindingResult bindingResult) throws NoContentException {
        if (bindingResult.hasErrors()) {
            log.error("Controller-Organisation-Post-Save-Error: " + organisation.toString());
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        log.info("Controller-Organisation-Post-Save: " + organisation.toString());
        return service.save(organisation);
    }

    @PutMapping()
    @ResponseBody
    public Organisation editOrganisation(@Valid Organisation organisation, BindingResult bindingResult) throws NoContentException {
        if (bindingResult.hasErrors()) {
            log.error("Controller-Organisation-Put-Edit-Error: " + organisation.toString());
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        return service.edit(organisation);

    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Organisation deleteOrganisation(@PathVariable Long id) throws NoContentException {
        log.info("Controller-Organisation-Delete");
        return service.logicalRemove(id);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Organisation showOrganisationById(@PathVariable Long id) throws NoContentException {
        log.info("Controller-Organisation-Get-FindById");
        return service.findById(id);
    }
    

}
