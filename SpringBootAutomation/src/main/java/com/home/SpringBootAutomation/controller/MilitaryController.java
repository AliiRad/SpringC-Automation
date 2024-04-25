package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Military;
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
        model.addAttribute("military",new Military());
        model.addAttribute("militaryList",militaryService.findAll());
        return "/military";
    }
    @GetMapping(value = "/id{id}")
    public String showMilitaryFindById(@ModelAttribute("id")Long id){
        log.info("Controller-MilitaryController-Get-FindById");
        Optional<Military>military=Optional.ofNullable(militaryService.findById(id));
        if (military.isPresent()){
            return "/military";
        }else{
            return "error-404";
        }
    }
//    @GetMapping(value = "/serialNumber")
//    public String showMilitaryFindBySerialNumber(Model model,@ModelAttribute("serialNumber")String serialNumber){
//        log.info("Controller-MilitaryController-Get-FindBySerialNumber");
//        List<Military> militaryList =militaryService.findBySerialNumber(serialNumber);
//        if (!militaryList.isEmpty()){
//            model.addAttribute("militaryList", militaryList);
//            return "/serialNumber";
//        }else {
//            return "error-404";
//        }
//    }

    @GetMapping(value = "/date")
    public String showMilitaryFindByIssuanceDate(Model model, @ModelAttribute("timeStamp") LocalDate issuanceDate){
        log.info("Controller-MilitaryController-Get-FindByIssuanceDate");
        List<Military> militaryList =militaryService.findByIssuanceDate(issuanceDate);
        if (!militaryList.isEmpty()){
            model.addAttribute("militaryList", militaryList);
            return "military";
        }else {
            return "errore-404";
        }
    }
    @PostMapping(value = "/save")
    public String saveMilitary(Military military){
        log.info("Controller-MilitaryController-Post-SaveMilitary: " + military.toString());
        log.info("Controller-MilitaryController-Post-SaveMilitary");
        militaryService.save(military);
        return "redirect:/Military";
    }
    @PostMapping(value = "/edit")
    public String editMilitary(Military military){
        log.info("Controller-MilitaryController-Post-EditMilitary");
        militaryService.edit(military);
        return "redirect:/Military";
    }
    @PostMapping(value = "/vitiation")
    public String militaryVitiation(Military military){
        log.info("Controller-MilitaryController-Post-militaryVitiation");
        militaryService.militaryVitiation(military.getId());
        return "redirect:/Military";
    }
    @PostMapping(value = "/delete")
    public String militaryDeleted(Military military){
        log.info("Controller-MilitaryController-Post-militaryDeleted");
        militaryService.militaryVitiation(military.getId());
        return "redirect:/military";
    }

}
