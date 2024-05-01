package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Disease;
import com.home.SpringBootAutomation.model.Disease;
import com.home.SpringBootAutomation.service.impl.DiseaseServiceImp;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping(value = "/disease")
public class DiseaseController {
    private DiseaseServiceImp diseaseServiceImp;

    public DiseaseController (DiseaseServiceImp diseaseServiceImp) {
        this.diseaseServiceImp = diseaseServiceImp;
    }


    @PostMapping(value = "/save")
    public String saveDisease(@Valid Disease disease, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                return "disease";
            } else {
                log.info("Disease_Save_Controller");
                diseaseServiceImp.save(disease);
                model.addAttribute("messageType", "success");
                model.addAttribute("messageContent", "Disease Saved successfully");
                return "redirect:/disease";
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/disease";

        }
    }

    @PutMapping(value = "/edit")
    public String editDisease(Disease disease, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                throw new ValidationException(result.getAllErrors().toString());
            }
            log.info("Disease_Edit_Controller");
            diseaseServiceImp.save(disease);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Disease edited successfully");
            return "redirect:/Disease";

        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return null;

        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteDisease(@PathVariable Long id, Model model) {
        try {
            log.info("Disease_delete_Controller");
            diseaseServiceImp.logicalRemove(id);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Disease deleted successfully");
            return "redirect:/disease";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/disease";

        }
    }


    @GetMapping
    public String findAllHistories(Model model) {
        try {
            log.info("Disease_FindALL_Controller");
            model.addAttribute("disease", new Disease());
            model.addAttribute("diseaseList", diseaseServiceImp.findAll());
            return "disease";
        }catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/disease";

        }

    }

    @GetMapping(value = "/id/{id}")

    public Disease singleHistory(@PathVariable("id") Long id, Model model) {
        try{
            log.info("Disease_FindById_Controller");
            Disease disease = diseaseServiceImp.findById(id);
            if (disease != null) {
                model.addAttribute("messageType", "success");
                model.addAttribute("messageContent", "Active Disease With Id : " + id + " Was Found");
                return disease;
            } else {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", "Active Disease With Id : " + id + " Was Not Found");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return null;
        }

    }
}

