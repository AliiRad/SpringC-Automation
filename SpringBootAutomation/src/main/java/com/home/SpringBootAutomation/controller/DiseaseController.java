package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Disease;
import com.home.SpringBootAutomation.model.MedicalHistory;
import com.home.SpringBootAutomation.service.impl.DiseaseServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping(value = "/disease")
public class DiseaseController {
    private DiseaseServiceImp diseaseServiceImp;

    public DiseaseController (DiseaseServiceImp diseaseServiceImp) {
        this.diseaseServiceImp = diseaseServiceImp;
    }

    @PostMapping(value = "/save")
    public String saveDisease(Disease disease) {
        log.info("Disease_Save_Controller");
        diseaseServiceImp.save(disease);
        return "redirect:/disease";
    }

    @PostMapping(value = "/edit")
    public String editDisease(Disease disease) {
        log.info("Disease_Edit_Controller");
        diseaseServiceImp.save(disease);
        return "disease";
    }

    @PostMapping(value = "/delete")
    public String removeDisease(Disease disease) {
        log.info("Disease_Remove_Controller");
        diseaseServiceImp.remove(disease);
        return "redirect:/disease";
    }

    @GetMapping
    public String findAllDiseases(Model model) {
        log.info("Disease_FindALL_Controller");
        model.addAttribute("Disease", new Disease());
        model.addAttribute("Disease", diseaseServiceImp.findAll());
        return "disease";

    }

    @GetMapping(value = "/id/{id}")
    public String singleDisease(@PathVariable("id") Long id, Model model) {
        log.info("Disease_FindById_Controller");
        Disease disease = diseaseServiceImp.findById(id);
        if (disease != null) {
            model.addAttribute("disease", disease);
            return "disease";
        } else {
            return "redirect:/disease";

        }

    }
}
