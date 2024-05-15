package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Account;
import com.home.SpringBootAutomation.model.FinancialDocument;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.service.impl.AccountServiceImp;
import com.home.SpringBootAutomation.service.impl.FinancialDocumentServiceImp;
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
@RequestMapping("/financialDocument")
public class FinancialDocumentController {
    private final FinancialDocumentServiceImp fServiceImp;

    private final PersonServiceImpl personService;

    private final AccountServiceImp serviceImp;

    public FinancialDocumentController(FinancialDocumentServiceImp fServiceImp, PersonServiceImpl personService, AccountServiceImp serviceImp) {
        this.fServiceImp = fServiceImp;
        this.personService = personService;
        this.serviceImp = serviceImp;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String FinancialDocumentForm(Model model) {
        model.addAttribute("financialDocumentList", fServiceImp.findAll());
        model.addAttribute("financialDocument", new FinancialDocument());
        List<Person> person = personService.findAll();
        model.addAttribute("person", person);
        List<Account> account = serviceImp.findAll();
        model.addAttribute("account", account);
        return "financialDocument";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public FinancialDocument save(Model model, @Valid FinancialDocument financialDocument, BindingResult bindingResult) throws NoContentException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage
                            ).toList().toString()
            );
        }
        return fServiceImp.save(financialDocument);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public FinancialDocument edit(Model model, @Valid FinancialDocument financialDocument, BindingResult bindingResult) throws NoContentException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage
                            ).toList().toString()
            );
        }
        return fServiceImp.edit(financialDocument);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public FinancialDocument remove(Model model, @PathVariable Long id) throws NoContentException {
        return fServiceImp.remove(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public FinancialDocument findById(Model model, @PathVariable Long id) throws NoContentException {
        return fServiceImp.findById(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<FinancialDocument> findAll(Model model) throws NoContentException {
        return fServiceImp.findAll();
    }
}