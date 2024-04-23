package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.BankTransaction;
import com.home.SpringBootAutomation.service.impl.BankTransactionServiceImp;
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
public class BankTransactionController {
    private final BankTransactionServiceImp serviceImp;

    public BankTransactionController(BankTransactionServiceImp serviceImp) {
        this.serviceImp = serviceImp;
    }

    @GetMapping(value = "/bankTransaction")
    public String showBankTransactionList(Model model) {
        model.addAttribute("bankTransaction",new BankTransaction());
        model.addAttribute("bankTransactionList", serviceImp.findAll());
        return "bankTransaction";
    }

    @PostMapping(value = "/bankTransaction/save")
    public String saveBank(BankTransaction bankTransaction) {
        serviceImp.save(bankTransaction);
        return "redirect:/bankTransaction";
    }

    @GetMapping(value = "bankTransaction/edit/{id}")
    public String showEditPage(@PathVariable("id") Long id, Model model) {
        BankTransaction bankTransaction = serviceImp.findById(id);
        if (bankTransaction != null) {
            model.addAttribute("bankTransactionEdit", bankTransaction);
            return "bankTransactionEdit";
        }
        return "Invalid Id";
    }

    @PostMapping(value = "bankTransaction/update/{id}")
    public String editForm(@PathVariable("id") Long id, @Valid BankTransaction bankTransaction) {
        serviceImp.save(bankTransaction);
        return "redirect:/bankTransaction";
    }

    @GetMapping(value = "bankTransaction/delete/{id}")
    public String deleteBankTransaction(@PathVariable("id") long id) {
        BankTransaction bankTransaction = serviceImp.findById(id);
        if (bankTransaction!=null) {
            serviceImp.remove(bankTransaction);
            return "redirect:/bankTransaction";
        }
        return "Invalid Id";
    }
}
