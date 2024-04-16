package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.Model.MedicalHistory;
import com.home.SpringBootAutomation.service.impl.MedicalHistoryServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping(value = "/medical")
public class MedicalHistoryController {
    private MedicalHistoryServiceImp medicalHistoryServiceImp;

    public MedicalHistoryController(MedicalHistoryServiceImp medicalHistoryServiceImp) {
        this.medicalHistoryServiceImp = medicalHistoryServiceImp;
    }

    @PostMapping(value = "/save")
    public String saveMedicalHistory(MedicalHistory medicalHistory) {
        log.info("Medical_Save_Controller");
        medicalHistoryServiceImp.save(medicalHistory);
        return "redirect:/medicalHistory";
    }

    @PostMapping(value = "/edit")
    public String editMedicalHistory(MedicalHistory medicalHistory) {
        log.info("Medical_Edit_Controller");
        medicalHistoryServiceImp.save(medicalHistory);
        return "medicalHistory";
    }

    @PostMapping(value = "/delete")
    public String removeMedicalHistory(MedicalHistory medicalHistory) {
        log.info("Medical_Remove_Controller");
        medicalHistoryServiceImp.remove(medicalHistory);
        return "redirect:/medicalHistory";
    }

    @GetMapping
    public String findAllHistories(Model model) {
        log.info("Medical_FindALL_Controller");
        model.addAttribute("medicalHistory", new MedicalHistory());
        model.addAttribute("medicalHistoryList", medicalHistoryServiceImp.findAll());
        return "medicalHistory";

    }

    @GetMapping(value = "/id/{id}")
    public String singleHistory(@PathVariable("id") Long id, Model model) {
        log.info("Medical_FindById_Controller");
        MedicalHistory medicalHistory = medicalHistoryServiceImp.findById(id);
        if (medicalHistory != null) {
            model.addAttribute("medicalHistory", medicalHistory);
            return "medicalHistory";
        } else {
            return "redirect:/medicalHistory";

        }

    }
}
