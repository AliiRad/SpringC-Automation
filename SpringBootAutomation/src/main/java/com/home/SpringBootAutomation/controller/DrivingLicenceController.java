package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.DrivingLicence;
import com.home.SpringBootAutomation.service.impl.DrivingLicenceServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(value = "/drivingLicence")
@Slf4j
public class DrivingLicenceController {


    private final DrivingLicenceServiceImpl service;

    public DrivingLicenceController(DrivingLicenceServiceImpl service) {
        this.service = service;
    }


    @PostMapping(value = "/save")
    public String save(@Valid DrivingLicence drivingLicence, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.info("Controller-DrivingLicence-Post-Save Failed ! ---> " + result.getAllErrors());
                return "drivingLicence";

            }
            service.save(drivingLicence);
            log.info("Controller - DrivingLicence Saved - Post Method ---->" + " drivingLicence :" + drivingLicence.toString());

            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "DrivingLicence Saved successfully");
            return "redirect:/drivingLicence";

        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/drivingLicence";
        }
    }


    @PostMapping(value = "/edit")
    public String edit(@Valid DrivingLicence drivingLicence, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error("Controller - DrivingLicence Edit Failed ! --->" + result.getAllErrors());
                return "drivingLicence";

            }
            service.update(drivingLicence);
            log.info("Controller - drivingLicence Edited - Put Method ---->" + " drivingLicence :" + drivingLicence);

            model.addAttribute("drivingLicence", drivingLicence);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "drivingLicence Saved successfully");
            return "redirect:/drivingLicence";

        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/drivingLicence";
        }
    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
//
            service.logicalRemove(id);

            log.info("The DrivingLicence With Id :" + id + " Successfully Deleted");
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "DrivingLicence With Id : " + id + " Successfully Deleted .");
            return "redirect:/drivingLicence";

        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "error-page";
            //TODO: Maybe a 500 error page ?
        }
    }


    @GetMapping
    public String findAll(Model model) {
        try {

            List<DrivingLicence> drivingLicenceList = service.findAll();
            log.info("Controller - Find All DrivingLicence  - Get Method");

            model.addAttribute("drivingLicence", new DrivingLicence());
            model.addAttribute("drivingLicenceList", drivingLicenceList);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "DrivingLicense List Is Not Empty");

            return "drivingLicence";


        } catch (Exception e) {

            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());

            return "error-page";
        }
    }


//    @GetMapping("/findById/{id}")
//    public ResponseEntity<Optional<DrivingLicence>> findById(@PathVariable("id") Long id, Model model) {
//        try {
//
//            // TODO: Fox output of findByIdAndDeletedFalse method in service. please change the name of services and  write exact name of services Like -> drivingLicenceService for better understanding
////            Optional<DrivingLicence> drivingLicence = service.findByIdAndDeletedFalse(id);
//
//            return ResponseEntity.ok(drivingLicence);
//        } catch (Exception e) {
//
//            log.error(e.getMessage());
//            model.addAttribute("messageType", "error");
//            model.addAttribute("messageContent", e.getMessage());
//
//            //TODO: return Error Page .
//
//            //return ResponseEntity.noContent();
//            return null;
//        }
//
//    }

}
