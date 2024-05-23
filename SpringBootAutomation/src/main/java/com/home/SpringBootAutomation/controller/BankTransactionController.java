package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.BankTransaction;
import com.home.SpringBootAutomation.model.FinancialDocument;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.service.impl.BankTransactionServiceImp;
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
@RequestMapping("/bankTransaction")
public class BankTransactionController {
    private final BankTransactionServiceImp serviceImp;

    private final PersonServiceImpl personService;

    private final FinancialDocumentServiceImp financialDocumentService;

    public BankTransactionController(BankTransactionServiceImp serviceImp, PersonServiceImpl personService, FinancialDocumentServiceImp financialDocumentService) {
        this.serviceImp = serviceImp;
        this.personService = personService;
        this.financialDocumentService = financialDocumentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String BankTransactionForm(Model model) {
        model.addAttribute("bankTransactionList", serviceImp.findAll());
        model.addAttribute("bankTransaction", new BankTransaction());
        List<Person> person = personService.findAll();
        model.addAttribute("person", person);
        List<FinancialDocument> financialDocument = financialDocumentService.findAll();
        model.addAttribute("financialDocument", financialDocument);
        return "bankTransaction";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BankTransaction save(Model model, @Valid BankTransaction bankTransaction, BindingResult bindingResult) throws NoContentException{
        System.out.println("Controller - Save" + bankTransaction);
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage
                            ).toList().toString()
            );
        }
        return serviceImp.save(bankTransaction);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public BankTransaction edit(Model model, @Valid BankTransaction bankTransaction, BindingResult bindingResult) throws NoContentException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage
                            ).toList().toString()
            );
        }
        return serviceImp.edit(bankTransaction);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public BankTransaction remove(Model model, @PathVariable Long id) throws NoContentException {
        return serviceImp.remove(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BankTransaction findById(Model model, @PathVariable Long id) throws NoContentException {
        return serviceImp.findById(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<BankTransaction> findAll(Model model) throws NoContentException {
        return serviceImp.findAll();
    }
}