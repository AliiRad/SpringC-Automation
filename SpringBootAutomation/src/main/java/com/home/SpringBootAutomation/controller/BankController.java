package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.Model.Bank;
import com.home.SpringBootAutomation.service.BankServiceImp;
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
public class BankController {
    private BankServiceImp serviceImp;

    public BankController(BankServiceImp serviceImp) {
        this.serviceImp = serviceImp;
    }

    @GetMapping(value = "/bank")
    public String showBankList(Model model) {
        model.addAttribute("bank",new Bank());
        model.addAttribute("bankList", serviceImp.findAll());
        return "bank";
    }

    @PostMapping(value = "/bank/save")
    public String saveBank(Bank bank) {
        serviceImp.save(bank);
        return "redirect:/bank";
    }

    @GetMapping(value = "/edit/{id}")
    public String showEditPage(@PathVariable("id") Long id, Model model) {
        Bank bank = serviceImp.findById(id);
        if (bank != null) {
            model.addAttribute("bankEdit", bank);
            return "bankEdit";
        }
        return "Invalid Id";
    }

    @PostMapping(value = "/update/{id}")
    public String editForm(@PathVariable("id") Long id, @Valid Bank bank) {
        serviceImp.save(bank);
        return "redirect:/bank";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteBank(@PathVariable("id") long id) {
        Bank bank = serviceImp.findById(id);
        if (bank!=null) {
            serviceImp.remove(bank);
            return "redirect:/bank";
        }
        return "Invalid Id";
    }
}
