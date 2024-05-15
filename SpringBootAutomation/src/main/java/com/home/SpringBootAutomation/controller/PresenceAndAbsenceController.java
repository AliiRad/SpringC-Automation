package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.PresenceAndAbsence;
import com.home.SpringBootAutomation.service.impl.PresenceAndAbsenceServiceImpl;
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
@RequestMapping("/presenceAndAbsence")
@Slf4j
public class PresenceAndAbsenceController {

    private final PresenceAndAbsenceServiceImpl presenceAndAbsenceService;

    public PresenceAndAbsenceController(PresenceAndAbsenceServiceImpl presenceAndAbsenceService) {
        this.presenceAndAbsenceService = presenceAndAbsenceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String presenceAndAbsenceForm(Model model) {
        model.addAttribute("presenceList", presenceAndAbsenceService.findPresenceAndAbsenceByDeletedFalse());
        model.addAttribute("presenceAndAbsence", new PresenceAndAbsence());
        return "presenceAndAbsence";
    }

    //todo : employee hasn't been set yet
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PresenceAndAbsence save(Model model, @Valid PresenceAndAbsence presenceAndAbsence, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        return presenceAndAbsenceService.save(presenceAndAbsence);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public PresenceAndAbsence edit(Model model, @Valid PresenceAndAbsence presenceAndAbsence, BindingResult bindingResult) throws NoContentException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        return presenceAndAbsenceService.update(presenceAndAbsence);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public PresenceAndAbsence remove(Model model, @PathVariable Long id) throws NoContentException {
        return presenceAndAbsenceService.logicalRemove(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PresenceAndAbsence findById(Model model, @PathVariable Long id) throws NoContentException {
        return presenceAndAbsenceService.findById(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<PresenceAndAbsence> findAll(Model model) throws NoContentException {
        return presenceAndAbsenceService.findAll();
    }

}
