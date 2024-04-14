package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.Model.DrivingLicenceModel;
import com.home.SpringBootAutomation.service.Impl.DrivingLicenceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(value = "/drivingLicence")
public class DrivingLicenceController {
    private DrivingLicenceServiceImpl drivingLicenceService;

    public DrivingLicenceController(DrivingLicenceServiceImpl drivingLicenceService) {
        this.drivingLicenceService = drivingLicenceService;
    }
    @GetMapping
    public String showDrivingLicence(Model model){
        log.info("Controller-DrivingLicenceController-Get-FindAll");
        model.addAttribute("drivingLicence",new DrivingLicenceModel());
        model.addAttribute("drivingLicenceList",drivingLicenceService.findAll());
        return "drivingLicence";
    }

    @GetMapping(value = "/id{id}")
    public String showDrivingLicenceFindById(@ModelAttribute("id")Long id){
        log.info("Controller-DrivingLicenceController-Get-FindById");
        Optional<DrivingLicenceModel> drivingLicence= Optional.ofNullable(drivingLicenceService.findById(id));
        if (drivingLicence.isPresent()){
            return "drivingLicence";
        }else {
            return "error-404";
        }
    }

    @GetMapping(value = "/serialNumber")
    public String showDrivingLicenceFindBySerialNumber(Model model,@ModelAttribute("serialNumber")String serialNumber){
        log.info("Controller-DrivingLicenceController-Get-FindBySerialNumber");
        List<DrivingLicenceModel> drivingLicenceModelList =drivingLicenceService.findBySerialNumber(serialNumber);
        if (!drivingLicenceModelList.isEmpty()) {
            model.addAttribute("drivingLicenceList", drivingLicenceModelList);
            return "serialNumber";
        }else {
            return "error-404";
        }

    }
    @GetMapping(value = "/date")
    public String showDrivingLicenceFindByIssuanceDate(Model model, @ModelAttribute("timeStamp") LocalDateTime issuanceDate){
        log.info("Controller-DrivingLicenceController-Get-FindByIssuanceDate");
        List<DrivingLicenceModel> drivingLicenceModelList =drivingLicenceService.findByDate(issuanceDate);
        if (!drivingLicenceModelList.isEmpty()){
            model.addAttribute("drivingLicenceList", drivingLicenceModelList);
            return "drivingLicence";
        }else {
            return "error-404";
        }
    }

    @PostMapping(value = "/save")
    public  String saveDrivingLicence(DrivingLicenceModel drivingLicenceModel){
        log.info("Controller-DrivingLicenceController-Post-SaveDrivingLicence: "+ drivingLicenceModel.toString());
        log.info("Controller-DrivingLicenceController-Post-SaveDrivingLicence");
        drivingLicenceService.save(drivingLicenceModel);
        return "redirect:/drivingLicence";
    }
    @PostMapping(value = "/edit")
    public String editDrivingLicence(DrivingLicenceModel drivingLicenceModel){
        log.info("Controller-DrivingLicenceController-Post-EditDrivingLicence");
        drivingLicenceService.edit(drivingLicenceModel);
        return "drivingLicence";
    }
    @PostMapping(value = "/suspension")
    public String drivingLicenceSuspension(DrivingLicenceModel drivingLicenceModel){
        log.info("Controller-DrivingLicenceController-Post-DrivingLicenceSuspension");
        drivingLicenceService.licenseSuspension(drivingLicenceModel.getId());
        return "redirect:/drivingLicence";
    }
    @PostMapping(value = "/delete")
    public String drivingLicenceDeleted(DrivingLicenceModel drivingLicenceModel) {
        log.info("Controller-DrivingLicenceController-Post-DrivingLicenceDeleted");
        drivingLicenceService.removeById(drivingLicenceModel.getId());
        return "redirect:/drivingLicence";
    }

}
