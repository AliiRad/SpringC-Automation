package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.Model.Bank;
import com.home.SpringBootAutomation.service.BankServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = "/bank")
public class BankController {
    private final BankServiceImp serviceImp;

    public BankController(BankServiceImp serviceImp) {
        this.serviceImp = serviceImp;
    }

    @GetMapping
    public String showBank(Model model) {
        log.info("Controller-Bank-Get-FindAll");
        model.addAttribute("bank", new Bank());
        model.addAttribute("bankList", serviceImp.findAll());
        return "bank";
    }

    @PostMapping(value = "/save")
    public String saveBank(Bank bank) {
        log.info("Controller-Bank-Save: " + bank.toString());
        serviceImp.save(bank);
        return "redirect:/bank";
    }

    @GetMapping(value ="/id/{id}")
    public String showBank(@PathVariable("id") Long id , Model model){
        Bank bank = serviceImp.findById(id);
        if (bank != null){
            model.addAttribute("bank", bank);
            return "bank";
        }else {
            return "redirect:/bank";
        }
    }

    @PostMapping(value ="/edit")
    public String editBank(Bank bank) {
        serviceImp.edit(bank);
        return "bank";
    }

    @PostMapping(value ="/delete")
    public String deleteBank(Long id) {
        log.info("Controller-Bank-Delete: " + id);
        serviceImp.logicalRemove(id);
        return "redirect:/bank";
    }
}
