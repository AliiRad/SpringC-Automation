package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.MedicalHistory;
import com.home.SpringBootAutomation.model.Ticket;
import com.home.SpringBootAutomation.service.impl.MedicalHistoryServiceImp;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping(value = "/medicalHistory")
public class MedicalHistoryController {
    private MedicalHistoryServiceImp medicalHistoryServiceImp;

    public MedicalHistoryController(MedicalHistoryServiceImp medicalHistoryServiceImp) {
        this.medicalHistoryServiceImp = medicalHistoryServiceImp;
    }

    @PostMapping(value = "/save")
    public String saveMedicalHistory(@Valid MedicalHistory medicalHistory, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                return "medicalHistory";
            } else {
                log.info("Medical_Save_Controller");
                medicalHistoryServiceImp.save(medicalHistory);
                model.addAttribute("messageType", "success");
                model.addAttribute("messageContent", "medicalHistory Saved successfully");
                return "redirect:/medicalHistory";
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/medicalHistory";

        }
    }

    @PutMapping(value = "/edit")
    public String editMedicalHistory(MedicalHistory medicalHistory, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                throw new ValidationException(result.getAllErrors().toString());
            }
            log.info("Medical_Edit_Controller");
            medicalHistoryServiceImp.save(medicalHistory);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "medicalHistory edited successfully");
            return "redirect:/medicalHistory";

        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return null;

        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteMedicalHistory(@PathVariable Long id, Model model) {
        try {
            log.info("Medical_delete_Controller");
            medicalHistoryServiceImp.logicalRemove(id);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "medicalHistory deleted successfully");
            return "redirect:/medicalHistory";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/medicalHistory";

        }
    }


    @GetMapping
    public String findAllHistories(Model model) {
        try {
            log.info("Medical_FindALL_Controller");
            model.addAttribute("medicalHistory", new MedicalHistory());
            model.addAttribute("medicalHistoryList", medicalHistoryServiceImp.findAll());
            return "medicalHistory";
        }catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/medicalHistory";

        }

    }

    @GetMapping(value = "/id/{id}")

    public MedicalHistory singleHistory(@PathVariable("id") Long id, Model model) {
        try{
        log.info("Medical_FindById_Controller");
        MedicalHistory medicalHistory = medicalHistoryServiceImp.findById(id);
        if (medicalHistory != null) {
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Active medicalHistory With Id : " + id + " Was Found");
            return medicalHistory;
        } else {
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", "Active medicalHistory With Id : " + id + " Was Not Found");
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



