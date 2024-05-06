package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Account;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.service.impl.AccountServiceImp;
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
@RequestMapping("/account")
public class AccountController {
    private final AccountServiceImp serviceImp;

    @Autowired
    private PersonServiceImpl personService;

    public AccountController(AccountServiceImp serviceImp) {
        this.serviceImp = serviceImp;
    }

    @PostMapping("/save")
    public String save(@Valid Account account, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error("Controller - Account Save Failed ! --->" + result.getAllErrors());
                return "account";
            }
            serviceImp.save(account);
            log.info("Controller - Account Saved - Post Method ---->" + "account: " + account.toString());
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Account Saved successfully");
            return "redirect:/account";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/account";
        }
    }

    @PutMapping("/edit")
    public String edit(@Valid @RequestBody Account account, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error("Controller - Account Edit Failed ! --->" + result.getAllErrors());
                return "account";
            }
            serviceImp.update(account);
            log.info("Controller - Account Edited - Put Method ---->" + " account :" + account.toString());
            model.addAttribute("account", account);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Account Saved successfully");
            return "redirect:/account";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/account";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            serviceImp.logicalRemove(id);

            log.info("The Account With Id :" + id + " Successfully Deleted");
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Account With Id : " + id + " Successfully Deleted .");
            return "redirect:/account";
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
            List<Account> personList = serviceImp.findAccountByDeletedFalse();
            log.info("Controller - Find Accounts With Deleted False - Get Method");
            model.addAttribute("account", new Account());
            model.addAttribute("accountList", personList);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Account List Is Not Empty");
            List<Person> person = personService.findAll();
            model.addAttribute("person", person);
            return "account";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<Account>> findById(@PathVariable("id") Long id, Model model) {
        try {
            Optional<Account> account = serviceImp.findAccountByIdAndDeletedFalse(id);
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return null;
        }
    }
}