package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.BankTransaction;
import com.home.SpringBootAutomation.model.FinancialDocument;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.service.impl.BankTransactionServiceImp;
import com.home.SpringBootAutomation.service.impl.FinancialDocumentServiceImp;
import com.home.SpringBootAutomation.service.impl.PersonServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
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

    @PostMapping("/save")
    public String save(@Valid BankTransaction bankTransaction, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error("Controller - BankTransaction Save Failed ! --->" + result.getAllErrors());
                return "bankTransaction";
            }
            serviceImp.save(bankTransaction);
            log.info("Controller - BankTransaction Saved - Post Method ---->" + "bankTransaction: " + bankTransaction.toString());
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "BankTransaction Saved successfully");
            return "redirect:/bankTransaction";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/bankTransaction";
        }
    }

    @PutMapping("/edit")
    public String edit(@Valid @RequestBody BankTransaction bankTransaction, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error("Controller - BankTransaction Edit Failed ! --->" + result.getAllErrors());
                return "bankTransaction";
            }
            serviceImp.update(bankTransaction);
            log.info("Controller - BankTransaction Edited - Put Method ---->" + " bankTransaction:" + bankTransaction);
            model.addAttribute("bankTransaction", bankTransaction);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "BankTransaction Saved successfully");
            return "redirect:/bankTransaction";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/bankTransaction";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            serviceImp.logicalRemove(id);
            log.info("The BankTransaction With Id :" + id + " Successfully Deleted");
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "BankTransaction With Id : " + id + " Successfully Deleted .");
            return "redirect:/bankTransaction";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping
    public String findAll(Model model) {
        try {
            List<BankTransaction> bankTransactionList = serviceImp.findBankTransactionByDeletedFalse();
            log.info("Controller - Find BankTransactions With Deleted False - Get Method");
            model.addAttribute("bankTransaction", new BankTransaction());
            model.addAttribute("bankTransactionList", bankTransactionList);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "BankTransaction List Is Not Empty");
            List<Person> person = personService.findAll();
            model.addAttribute("person", person);
            List<FinancialDocument> financialDocument = financialDocumentService.findAll();
            model.addAttribute("financialDocument", financialDocument);
            return "bankTransaction";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<BankTransaction>> findById(@PathVariable("id") Long id) {
        try {
            Optional<BankTransaction> bankTransaction = serviceImp.findBankTransactionByIdAndDeletedFalse(id);
            if (bankTransaction.isPresent()) {
                return ResponseEntity.ok(bankTransaction);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}