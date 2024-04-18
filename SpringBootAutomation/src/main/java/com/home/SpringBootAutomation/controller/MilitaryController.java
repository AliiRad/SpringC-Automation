package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.MilitaryServiceModel;
import com.home.SpringBootAutomation.service.impl.MilitaryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(value = "/military")

public class MilitaryController {
    private MilitaryServiceImpl militaryService;

    public MilitaryController(MilitaryServiceImpl militaryService) {
        this.militaryService = militaryService;
    }
    @GetMapping
    public String showMilitary(Model model){
        log.info("Controller-MilitaryController-Get-FindAll");
        model.addAttribute("military",new MilitaryServiceModel());
        model.addAttribute("militaryList",militaryService.findAll());
        return "military";
    }
    @GetMapping(value = "/id{id}")
    public String showMilitaryFindById(@ModelAttribute("id")Long id){
        log.info("Controller-MilitaryController-Get-FindById");
        Optional<MilitaryServiceModel>military=Optional.ofNullable(militaryService.findById(id));
        if (military.isPresent()){
            return "military";
        }else{
            return "error-404";
        }
    }
    @GetMapping(value = "/serialNumber")
    public String showMilitaryFindBySerialNumber(Model model,@ModelAttribute("serialNumber")String serialNumber){
        log.info("Controller-MilitaryController-Get-FindBySerialNumber");
        List<MilitaryServiceModel> militaryServiceModelList =militaryService.findBySerialNumber(serialNumber);
        if (!militaryServiceModelList.isEmpty()){
            model.addAttribute("militaryList", militaryServiceModelList);
            return "serialNumber";
        }else {
            return "error-404";
        }
    }

    @GetMapping(value = "/date")
    public String showMilitaryFindByIssuanceDate(Model model, @ModelAttribute("timeStamp") LocalDate issuanceDate){
        log.info("Controller-MilitaryController-Get-FindByIssuanceDate");
        List<MilitaryServiceModel> militaryServiceModelList =militaryService.findByIssuanceDate(issuanceDate);
        if (!militaryServiceModelList.isEmpty()){
            model.addAttribute("militaryList", militaryServiceModelList);
            return "military";
        }else {
            return "errore-404";
        }
    }
    @PostMapping(value = "/save")
    public String saveMilitary(MilitaryServiceModel militaryServiceModel){
        log.info("Controller-MilitaryController-Post-SaveMilitary: " + militaryServiceModel.toString());
        log.info("Controller-MilitaryController-Post-SaveMilitary");
        militaryService.save(militaryServiceModel);
        return "redirect:/MilitaryServiceModel";
    }
    @PostMapping(value = "/edit")
    public String editMilitary(MilitaryServiceModel militaryServiceModel){
        log.info("Controller-MilitaryController-Post-EditMilitary");
        militaryService.edit(militaryServiceModel);
        return "redirect:/MilitaryServiceModel";
    }
    @PostMapping(value = "/vitiation")
    public String militaryVitiation(MilitaryServiceModel militaryServiceModel){
        log.info("Controller-MilitaryController-Post-militaryVitiation");
        militaryService.militaryVitiation(militaryServiceModel.getId());
        return "redirect:/MilitaryServiceModel";
    }
    @PostMapping(value = "/delete")
    public String militaryDeleted(MilitaryServiceModel militaryServiceModel){
        log.info("Controller-MilitaryController-Post-militaryDeleted");
        militaryService.militaryVitiation(militaryServiceModel.getId());
        return "redirect:/military";
    }

}
