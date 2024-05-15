package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Account;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.service.impl.AccountServiceImp;
import com.home.SpringBootAutomation.service.impl.PersonServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    private final AccountServiceImp serviceImp;

    private final PersonServiceImpl personService;

    public AccountController(AccountServiceImp serviceImp, PersonServiceImpl personService) {
        this.serviceImp = serviceImp;
        this.personService = personService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String AccountForm(Model model) {
        model.addAttribute("accountList", serviceImp.findAll());
        model.addAttribute("account", new Account());
        List<Person> person = personService.findAll();
        model.addAttribute("person", person);
        return "account";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account save(Model model, @Valid Account account, BindingResult bindingResult) throws NoContentException{
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage
                            ).toList().toString()
            );
        }
        return serviceImp.save(account);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public Account edit(Model model, @Valid Account account, BindingResult bindingResult) throws NoContentException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage
                            ).toList().toString()
            );
        }
        return serviceImp.edit(account);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Account remove(Model model, @PathVariable Long id) throws NoContentException {
        return serviceImp.remove(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account findById(Model model, @PathVariable Long id) throws NoContentException {
        return serviceImp.findById(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Account> findAll(Model model) throws NoContentException {
        return serviceImp.findAll();
    }
}