package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Timesheet;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/timesheet")
@Slf4j
public class TimesheetController {

    private final TimesheetServiceImpl timesheetService;

    public TimesheetController(TimesheetServiceImpl timesheetService) {
        this.timesheetService = timesheetService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String timesheetForm(Model model) {
        model.addAttribute("timesheetList", timesheetService.findTimesheetByDeletedFalse());
        model.addAttribute("timesheet", new Timesheet());
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

}
