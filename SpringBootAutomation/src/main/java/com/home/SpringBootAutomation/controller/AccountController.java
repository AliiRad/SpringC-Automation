package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Account;
import com.home.SpringBootAutomation.service.impl.AccountServiceImp;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping
public class AccountController {
    private final AccountServiceImp serviceImp;

    public AccountController(AccountServiceImp serviceImp) {
        this.serviceImp = serviceImp;
    }

    @GetMapping(value = "/account")
    public String showAccountList(Model model) {
        model.addAttribute("account",new Account());
        model.addAttribute("accountList", serviceImp.findAll());
        return "account";
    }

    @PostMapping(value = "/account/save")
    public String saveAccount(Account account) {
        serviceImp.save(account);
        return "redirect:/account";
    }

    @GetMapping(value = "account/edit/{id}")
    public String showEditPage(@PathVariable("id") Long id, Model model) {
        Account account = serviceImp.findById(id);
        if (account != null) {
            model.addAttribute("accountEdit", account);
            return "accountEdit";
        }
        return "Invalid Id";
    }

    @PostMapping(value = "account/update/{id}")
    public String editForm(@PathVariable("id") Long id, @Valid Account account) {
        serviceImp.save(account);
        return "redirect:/account";
    }

    @GetMapping(value = "account/delete/{id}")
    public String deleteAccount(@PathVariable("id") long id) {
        Account account = serviceImp.findById(id);
        if (account!=null) {
            serviceImp.remove(account);
            return "redirect:/account";
        }
        return "Invalid Id";
    }
}
