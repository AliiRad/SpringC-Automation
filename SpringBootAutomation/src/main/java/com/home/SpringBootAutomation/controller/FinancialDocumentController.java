package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Account;
import com.home.SpringBootAutomation.model.FinancialDocument;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.service.impl.AccountServiceImp;
import com.home.SpringBootAutomation.service.impl.FinancialDocumentServiceImp;
import com.home.SpringBootAutomation.service.impl.PersonServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/financialDocument")
public class FinancialDocumentController {
    private final FinancialDocumentServiceImp fServiceImp;

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    private AccountServiceImp serviceImp;

    public FinancialDocumentController(FinancialDocumentServiceImp fServiceImp) {
        this.fServiceImp = fServiceImp;
    }

    @PostMapping("/save")
    public String save(@Valid FinancialDocument financialDocument, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error("Controller - Financial Document Save Failed ! --->" + result.getAllErrors());
                return "financialDocument";
            }
            fServiceImp.save(financialDocument);
            log.info("Controller - Financial Document Saved - Post Method ---->" + "financialDocument: " + financialDocument.toString());
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Financial Document Saved successfully");
            return "redirect:/financialDocument";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/financialDocument";
        }
    }

    @PutMapping("/edit")
    public String edit(@Valid @RequestBody FinancialDocument financialDocument, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error("Controller - Financial Document Edit Failed! --->" + result.getAllErrors());
                return "financialDocument";
            }
            fServiceImp.update(financialDocument);
            log.info("Controller - Financial Document Edited - Put Method ---->" + " financialDocument :" + financialDocument.toString());
            model.addAttribute("financialDocument", financialDocument);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Financial Document Saved successfully");
            return "redirect:/financialDocument";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/financialDocument";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            fServiceImp.logicalRemove(id);
            log.info("The Financial Document With Id:" + id + " Successfully Deleted");
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Financial Document With Id: " + id + " Successfully Deleted.");
            return "redirect:/financialDocument";
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
            List<FinancialDocument> financialDocumentList = fServiceImp.findFinancialDocumentByDeletedFalse();
            log.info("Controller - Find Financial Documents With Deleted False - Get Method");
            model.addAttribute("financialDocument", new FinancialDocument());
            model.addAttribute("financialDocumentList", financialDocumentList);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Financial Document List Is Not Empty");
            List<Person> person = personService.findAll();
            model.addAttribute("person", person);
            List<Account> account = serviceImp.findAll();
            model.addAttribute("account", account);
            return "financialDocument";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<FinancialDocument>> findById(@PathVariable("id") Long id) {
        try {
            Optional<FinancialDocument> financialDocument = fServiceImp.findFinancialDocumentByIdAndDeletedFalse(id);
            if (financialDocument.isPresent()) {
                return ResponseEntity.ok(financialDocument);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}