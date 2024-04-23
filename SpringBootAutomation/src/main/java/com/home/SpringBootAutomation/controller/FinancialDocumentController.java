package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.FinancialDocument;
import com.home.SpringBootAutomation.service.impl.FinancialDocumentServiceImp;
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
public class FinancialDocumentController {
    private final FinancialDocumentServiceImp serviceImp;

    public FinancialDocumentController(FinancialDocumentServiceImp serviceImp) {
        this.serviceImp = serviceImp;
    }

    @GetMapping(value = "/financialDocument")
    public String showFinancialDocumentList(Model model) {
        model.addAttribute("financialDocument",new FinancialDocument());
        model.addAttribute("financialDocumentList", serviceImp.findAll());
        return "financialDocument";
    }

    @PostMapping(value = "/financialDocument/save")
    public String saveFinancialDocument(FinancialDocument financialDocument) {
        serviceImp.save(financialDocument);
        return "redirect:/financialDocument";
    }

    @GetMapping(value = "financialDocument/edit/{id}")
    public String showEditPage(@PathVariable("id") Long id, Model model) {
        FinancialDocument financialDocument = serviceImp.findById(id);
        if (financialDocument != null) {
            model.addAttribute("financialDocumentEdit", financialDocument);
            return "financialDocumentEdit";
        }
        return "Invalid Id";
    }

    @PostMapping(value = "financialDocument/update/{id}")
    public String editForm(@PathVariable("id") Long id, @Valid FinancialDocument financialDocument) {
        serviceImp.save(financialDocument);
        return "redirect:/financialDocument";
    }

    @GetMapping(value = "financialDocument/delete/{id}")
    public String deleteFinancialDocument(@PathVariable("id") long id) {
        FinancialDocument financialDocument = serviceImp.findById(id);
        if (financialDocument!=null) {
            serviceImp.remove(financialDocument);
            return "redirect:/financialDocument";
        }
        return "Invalid Id";
    }
}