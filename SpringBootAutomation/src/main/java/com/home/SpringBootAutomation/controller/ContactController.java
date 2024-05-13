package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Contact;
import com.home.SpringBootAutomation.service.ContactService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String personForm(Model model) {
        model.addAttribute("contactList", contactService.findAll());
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Contact save(Model model, @Valid Contact contact, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage())
                            .collect(Collectors.toList()).toString()
            );
        }
        return contactService.save(contact);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public Contact edit(Model model, @Valid Contact contact, BindingResult bindingResult) throws NoContentException {
        if (bindingResult.hasErrors()){
           throw new ValidationException(
                   bindingResult
                           .getAllErrors()
                           .stream()
                           .map((event) -> event.getDefaultMessage())
                           .collect(Collectors.toList()).toString()

           );
        }
        return contactService.edit(contact);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Contact remove(Model model, @PathVariable Long id) throws NoContentException {
        return contactService.remove(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method =  RequestMethod.GET)
    public Contact findById(Model model, @PathVariable Long id) throws NoContentException {
        return contactService.findById(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Contact> findAl(Model model) throws NoContentException{
        return contactService.findAll();
    }
}

