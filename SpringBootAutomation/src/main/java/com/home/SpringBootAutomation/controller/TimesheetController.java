package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.model.Timesheet;
import com.home.SpringBootAutomation.service.impl.PersonServiceImpl;
import com.home.SpringBootAutomation.service.impl.TimesheetServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/timesheet")
public class TimesheetController {

    private final TimesheetServiceImpl timesheetService;
    private final PersonServiceImpl personService;

    public TimesheetController(TimesheetServiceImpl timesheetService, PersonServiceImpl personService) {
        this.timesheetService = timesheetService;
        this.personService = personService;
    }

    //timesheet table - findAll
    @GetMapping("/timesheetTable")
    public String showSalaryList(Model model){
        log.info("Timesheet Table - Get");
        try {
            model.addAttribute("timesheet", new Timesheet());
            model.addAttribute("timesheetList", timesheetService.findTimesheetByDeletedFalse());
            return "timesheetTable";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //timesheet form
    @GetMapping("/timesheetForm")
    public String showForm(@ModelAttribute("name") String name, @ModelAttribute("lastname") String lastname, Model model) {
        log.info("Timesheet Form - Get");
        try {
            model.addAttribute("timesheet", new Timesheet());
            model.addAttribute("person", new Person());
            model.addAttribute("personList", personService.findPersonByNameAndLastnameAndDeletedFalse(name,lastname));
            return "timesheetForm";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //timesheet save
    @PostMapping(value = "/save")
    public String save(@Valid Timesheet timesheet, BindingResult result, Model model){
        log.info("Timesheet Save - Post");
        if (result.hasErrors()){
            model.addAttribute("person", new Person());
            log.error(result.getAllErrors().toString());
            return "timesheetForm";
        }
        try {
//            Long id = person.getId();
//            Optional<Person> person1 = personService.findById(id);
//            person1.ifPresent(timesheet::setEmployee);
            Date.valueOf(timesheet.getDate());
            timesheetService.save(timesheet);
            log.info("Timesheet Saved");
            model.addAttribute("timesheet", new Timesheet());
            model.addAttribute("person", new Person());
            model.addAttribute("msg", "Timesheet Saved");
            return "timesheetForm";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //timesheet edit form
    @GetMapping(value = "/edit")
    public String showEditForm(@RequestParam Long id, Model model) {
        log.info("Timesheet - Edit Page");
        try {
            Optional<Timesheet> timesheet = timesheetService.findById(id);
            model.addAttribute("timesheet",timesheet);
            return "timesheetEdit";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //timesheet edit
    //todo not working
    @PostMapping(value = "/edit")
    public String edit(@Valid Timesheet timesheet, Model model){
        log.info("Timesheet - Edit");
        try {
            Long id = timesheet.getId();
            Optional<Timesheet> timesheet1 = timesheetService.findById(id);
            if (timesheet1.isPresent()){
                timesheetService.update(timesheet);
                log.info("Timesheet Edited");
                model.addAttribute("msg", "Timesheet Edited");
                return "timesheetEdit";
            }
            return "timesheetEdit";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //todo I cant show the error msg it gives 500 error
    //timesheet logical remove
    @PostMapping("/delete")
    public String softDelete(@ModelAttribute("id") Long id, @ModelAttribute("date") LocalDate date, Model model){
        log.info("Timesheet - Delete");
        try {
            Optional<Timesheet> timesheet = timesheetService.findById(id);
            if (timesheet.isPresent()){
                timesheet.get().setDate(date.plusYears(id+1000));
                timesheetService.save(timesheet.get());
                timesheetService.logicalRemove(id);
                log.info("Timesheet Removed");
                model.addAttribute("msg", "Timesheet Removed");
                return "timesheetTable";
            }
            return "timesheetTable";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //person nameAndFamily search form
    @GetMapping(value = "/findByNameAndLastname")
    public String findByNameAndLastname(@ModelAttribute("name") String name,@ModelAttribute("lastname") String lastname, Model model) {
        log.info("Person - findByNameAndLastname");
        try {
            if(name.isEmpty() && lastname.isEmpty()){
                model.addAttribute("msg1", "fill in the blanks");
            }
            model.addAttribute("person",new Person());
            model.addAttribute("timesheet",new Timesheet());
            List<Person> personList = personService.findPersonByNameAndLastnameAndDeletedFalse(name,lastname);
            if (personList != null){
                model.addAttribute("personList",personList);
            }
            return "timesheetForm";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //todo doesnt work
    //person select
    @GetMapping(value = "/selectPerson")
    public String selectPerson(@RequestParam Long id, Model model,BindingResult result) {
        log.info("Timesheet Form - Select person");
        try {
            Optional<Person> person = personService.findById(id);
            model.addAttribute("person",person);
            return "redirect:/timesheet/timesheetForm";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
