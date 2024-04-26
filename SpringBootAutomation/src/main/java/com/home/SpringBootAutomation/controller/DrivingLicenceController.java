package com.home.SpringBootAutomation.controller;


import com.home.SpringBootAutomation.model.DrivingLicence;
import com.home.SpringBootAutomation.service.DrivingLicenceService;

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
@RequestMapping(value = "/drivingLicence")
public class DrivingLicenceController {


    private final DrivingLicenceService drivingLicenceService;

    public DrivingLicenceController(DrivingLicenceService drivingLicenceService) {
        this.drivingLicenceService = drivingLicenceService;
    }
    @GetMapping
    public String showDrivingLicence(Model model){
        log.info("Controller-DrivingLicenceController-Get-FindAll");
        model.addAttribute("drivingLicence",new DrivingLicence());
        model.addAttribute("drivingLicenceList",drivingLicenceService.findAll());
        return "drivingLicence";
    }

    @GetMapping(value = "/id{id}")
    public String showDrivingLicenceFindById(@ModelAttribute("id")Long id){
        log.info("Controller-DrivingLicenceController-Get-FindById");
        Optional<DrivingLicence> drivingLicence= Optional.ofNullable(drivingLicenceService.findById(id));
        if (drivingLicence.isPresent()){
            return "drivingLicence";
        }else {
            return "error-404";
        }
    }

//    @GetMapping(value = "/serialNumber")
//    public String showDrivingLicenceFindBySerialNumber(Model model,@ModelAttribute("serialNumber")String serialNumber){
//        log.info("Controller-DrivingLicenceController-Get-FindBySerialNumber");
//        List<DrivingLicence> drivingLicenceList =drivingLicenceService.findBySerialNumber(serialNumber);
//        if (!drivingLicenceList.isEmpty()) {
//            model.addAttribute("drivingLicenceList", drivingLicenceList);
//            return "serialNumber";
//        }else {
//            return "error-404";
//        }

//    }

//    @GetMapping(value = "/date")
//    public String showDrivingLicenceFindByIssuanceDate(Model model, @ModelAttribute("timeStamp") LocalDate issuanceDate){
//        log.info("Controller-DrivingLicenceController-Get-FindByIssuanceDate");
//        List<DrivingLicence> drivingLicenceList =drivingLicenceService.findByDate(issuanceDate);
//        if (!drivingLicenceList.isEmpty()){
//            model.addAttribute("drivingLicenceList", drivingLicenceList);
//            return "drivingLicence";
//        }else {
//            return "error-404";
//        }
//    }

    @PostMapping(value = "/save")
    public  String saveDrivingLicence(DrivingLicence drivingLicence){
        log.info("Controller-DrivingLicenceController-Post-SaveDrivingLicence: "+ drivingLicence.toString());
        log.info("Controller-DrivingLicenceController-Post-SaveDrivingLicence");
        drivingLicenceService.save(drivingLicence);
        return "redirect:/drivingLicence";
    }
    @PostMapping(value = "/edit")
    public String editDrivingLicence(DrivingLicence drivingLicence){
        log.info("Controller-DrivingLicenceController-Post-EditDrivingLicence");
        drivingLicenceService.edit(drivingLicence);
        return "drivingLicence";
    }
//    @PostMapping(value = "/suspension")
//    public String drivingLicenceSuspension(DrivingLicence drivingLicence){
//        log.info("Controller-DrivingLicenceController-Post-DrivingLicenceSuspension");
//        drivingLicenceService.licenseSuspension(drivingLicence.getId());
//        return "redirect:/drivingLicence";
//    }
    @PostMapping(value = "/delete")
    public String drivingLicenceDeleted(DrivingLicence drivingLicence) {
        log.info("Controller-DrivingLicenceController-Post-DrivingLicenceDeleted");
        drivingLicenceService.removeById(drivingLicence.getId());
        return "redirect:/drivingLicence";
    }

}
