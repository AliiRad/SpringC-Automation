package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.AppointmentDecree;
import com.home.SpringBootAutomation.service.impl.AppointmentDecreeServiceImp;
import com.home.SpringBootAutomation.service.impl.PersonServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/appointmentDecree")
public class AppointmentDecreeController {

    private final AppointmentDecreeServiceImp service;

    @Autowired
    private PersonServiceImpl personService;

    public AppointmentDecreeController(AppointmentDecreeServiceImp service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String appointmentDecreeForm(Model model) {
        model.addAttribute("appointmentDecreeList", service.findAppointmentDecreeByDeletedFalse());
        model.addAttribute("appointmentDecree", new AppointmentDecree());
        model.addAttribute("person", personService.findAll());
        return "appointmentDecree";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AppointmentDecree save(Model model, @Valid AppointmentDecree appointmentDecree, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        return service.save(appointmentDecree);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public AppointmentDecree edit(Model model, @Valid AppointmentDecree appointmentDecree, BindingResult bindingResult) throws NoContentException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        return service.edit(appointmentDecree);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AppointmentDecree remove(Model model, @PathVariable Long id) throws NoContentException {
        return service.logicalRemove(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<AppointmentDecree> findById(Model model, @PathVariable("id") Long id) throws NoContentException {
        model.addAttribute("appointmentDecree", service.findAppointmentDecreeByIdAndDeletedFalse(id));
        return service.findAppointmentDecreeByIdAndDeletedFalse(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<AppointmentDecree> findByAll(Model model) {
        model.addAttribute("appointmentDecreeList", service.findAppointmentDecreeByDeletedFalse());
        return service.findAppointmentDecreeByDeletedFalse();
    }

}
