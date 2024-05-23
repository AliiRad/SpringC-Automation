package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.service.PersonService;
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
@RequestMapping("/person")
@Slf4j
public class PersonController {


    private final PersonService service;
    public PersonController(PersonService service){
        this.service = service;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String personForm(Model model){
        model.addAttribute("personList" , service.findPersonByDeletedFalse());
        model.addAttribute("person" , new Person());
        return "person";
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Person save(Model model , @Valid Person person , BindingResult result){
        if (result.hasErrors()){
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage())
                            .collect(Collectors.toList()).toString()
            );
        }
        return service.save(person);
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public Person edit(Model model , @Valid Person person , BindingResult result) throws NoContentException {
        if (result.hasErrors()){
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage())
                            .collect(Collectors.toList()).toString()
            );
        }
        return service.update(person);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Person remove(Model model, @PathVariable Long id) throws NoContentException {
        return service.logicalRemoveWithReturn(id);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Person> findById(Model model, @PathVariable Long id) throws NoContentException {

        return service.findPersonByIdAndDeletedFalse(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Person> findAll(Model model){

        return service.findPersonByDeletedFalse();
    }


}
