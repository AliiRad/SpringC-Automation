package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.MedicalHistory;
import com.home.SpringBootAutomation.service.impl.MedicalHistoryServiceImp;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping(value = "/medicalHistory")
public class MedicalHistoryController {
    private MedicalHistoryServiceImp medicalHistoryServiceImp;


    public MedicalHistoryController(MedicalHistoryServiceImp medicalHistoryServiceImp) {
        this.medicalHistoryServiceImp = medicalHistoryServiceImp;
    }

    @GetMapping
    public String findAllHistories(Model model) {

        log.info("Medical_FindALL_Controller");
        model.addAttribute("medicalHistory", new MedicalHistory());
        model.addAttribute("medicalHistoryList", medicalHistoryServiceImp.findAll());
        return "medicalHistory";


    }

    @PostMapping()
    @ResponseBody
    public MedicalHistory saveMedicalHistory(@Valid MedicalHistory medicalHistory, BindingResult bindingResult) throws NoContentException {

        if (bindingResult.hasErrors()) {
            log.error("Medical-Controller-Post-Save-Error:" + medicalHistory.toString());
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        log.info("Medical-Controller-Post-Save: " + medicalHistory.toString());
        return medicalHistoryServiceImp.save(medicalHistory);
    }


    @PutMapping()
    @ResponseBody
    public MedicalHistory editMedicalHistory(MedicalHistory medicalHistory, BindingResult bindingResult, Model model) throws NoContentException {
        if (bindingResult.hasErrors()) {
            log.error("Medical-Controller-Put-edit-Error:" + medicalHistory.toString());
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        return medicalHistoryServiceImp.edit(medicalHistory);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public MedicalHistory deleteMedicalHistory(@PathVariable Long id, Model model) throws NoContentException {
        log.info("Medical-Controller-Delete-Delete: " + id);
        return medicalHistoryServiceImp.logicalRemove(id);

    }


    @GetMapping(value = "/{id}")
    @ResponseBody
    public MedicalHistory singleHistory(@PathVariable("id") Long id) throws NoContentException {
        log.info("Medical-Controller-Get-FindById: " + id);
        return medicalHistoryServiceImp.findById(id);
    }
    }


