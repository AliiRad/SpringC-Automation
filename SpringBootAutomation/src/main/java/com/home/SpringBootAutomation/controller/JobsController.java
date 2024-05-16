package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Jobs;
import com.home.SpringBootAutomation.service.JobsService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/jobs")

public class JobsController {

    private final JobsService service ;
    public JobsController(JobsService service){
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String jobsForm(Model model){
        model.addAttribute("jobList" , service.findJobsByDeletedFalse());
        model.addAttribute("jobs" , new Jobs());
        return "jobs";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Jobs save(Model model , @Valid Jobs jobs , BindingResult result){
        if (result.hasErrors()){
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage())
                            .collect(Collectors.toList()).toString()
            );
        }
        return service.save(jobs);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public Jobs edit(Model model , @Valid Jobs jobs , BindingResult result) throws NoContentException {
        if (result.hasErrors()){
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage())
                            .collect(Collectors.toList()).toString()
            );
        }
        return service.update(jobs);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Jobs remove(Model model, @PathVariable Long id) throws NoContentException {
        return service.logicalRemoveWithReturn(id); //TODO: logicalRemove ---> void
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Jobs> findById(Model model, @PathVariable Long id) throws NoContentException {

        return service.findJobsByIdAndDeletedFalse(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Jobs> findAll(Model model){

        return service.findJobsByDeletedFalse();
    }






}
