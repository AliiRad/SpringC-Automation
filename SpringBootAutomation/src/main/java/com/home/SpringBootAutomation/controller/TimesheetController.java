package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.model.Timesheet;
import com.home.SpringBootAutomation.service.impl.PersonServiceImpl;
import com.home.SpringBootAutomation.service.impl.TimesheetServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/timesheet")
@Slf4j
public class TimesheetController {

    private final TimesheetServiceImpl timesheetService;

    private final PersonServiceImpl personService;

    public TimesheetController(TimesheetServiceImpl timesheetService, PersonServiceImpl personService) {
        this.timesheetService = timesheetService;
        this.personService = personService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String timesheetForm(Model model) {
        model.addAttribute("timesheetList", timesheetService.findTimesheetByDeletedFalse());
        model.addAttribute("timesheet", new Timesheet());
        model.addAttribute("person", new Person());
        return "timesheet";
    }

    //todo : employee and manager hasn't been set yet
    //todo : employee and manager signatures haven't been set yet
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Timesheet save(Model model, @Valid Timesheet timesheet, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        return timesheetService.save(timesheet);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public Timesheet edit(Model model, @Valid Timesheet timesheet, BindingResult bindingResult) throws NoContentException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        return timesheetService.update(timesheet);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Timesheet remove(Model model, @PathVariable Long id) throws NoContentException {
        return timesheetService.logicalRemove(id);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Timesheet findById(Model model, @PathVariable Long id) throws NoContentException {
        return timesheetService.findById(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Timesheet> findAll(Model model) {
        return timesheetService.findAll();
    }


    @RequestMapping(value = "/findEmployee", method = RequestMethod.GET)
    public String findEmployeeByNameAndFamily(Model model,@ModelAttribute("name") String name,@ModelAttribute("lastname") String lastname) {
        try {
            model.addAttribute("person",new Person());
            model.addAttribute("timesheet",new Timesheet());
            List<Person> personList = personService.findPersonByNameAndLastnameAndDeletedFalse(name,lastname);
            if (personList != null){
                model.addAttribute("personList",personList);
            }
            return "timesheet";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //todo : needs work doesnt work
    @GetMapping(value = "/selectPerson/{id}")
    public String selectPerson(@PathVariable Long id, Model model,BindingResult result) {
        log.info("Timesheet Form - Select person");
        try {
            Optional<Person> person = personService.findById(id);
            model.addAttribute("person",person);
            return "timesheet";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
