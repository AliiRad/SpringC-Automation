package com.home.SpringBootAutomation.controller;


import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Skills;
import com.home.SpringBootAutomation.service.SkillsService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/skills")
public class SkillsController {

    private final SkillsService service;
    public SkillsController(SkillsService service){
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String SkillsForm(Model model){
        model.addAttribute("skillList" , service.findSkillsByDeletedFalse());
        model.addAttribute("skills" , new Skills());
        return "skills";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Skills save(@Valid Skills skills , Model model , BindingResult result){
        if (result.hasErrors()){
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage())
                            .collect(Collectors.toList()).toString()
            );
        }
        return service.save(skills);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public Skills edit(@Valid Skills skills , BindingResult result , Model model) throws NoContentException {
        if (result.hasErrors()){
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage())
                            .collect(Collectors.toList()).toString()
            );
        }
        return service.update(skills);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Skills remove(Model model, @PathVariable Long id) throws NoContentException {
        return service.logicalRemoveWithReturn(id); //TODO: logicalRemove ---> void
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Skills> findById(Model model, @PathVariable Long id) throws NoContentException {
        return service.findSkillsByIdAndDeletedFalse(id);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Skills> findAll(Model model){
        return service.findSkillsByDeletedFalse();
    }
}
