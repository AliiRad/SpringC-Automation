package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.AppointmentDecree;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.model.Skills;
import com.home.SpringBootAutomation.service.impl.AppointmentDecreeServiceImp;
import com.home.SpringBootAutomation.service.impl.PersonServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/appointmentDecree")
public class AppointmentDecreeController {
    private final AppointmentDecreeServiceImp service;

    @Autowired
    private PersonServiceImpl personService;

    public AppointmentDecreeController(AppointmentDecreeServiceImp service) {
        this.service = service;
    }

    @PostMapping("/save")
    public String save(@Valid AppointmentDecree appointmentDecree, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());

                log.error("Controller - AppointmentDecree Save Failed ! --->" + result.getAllErrors());
                return "appointmentDecree";
            }
            service.save(appointmentDecree);
            log.info("Controller - AppointmentDecree Saved - Post Method ---->" + " appointmentDecree :" + appointmentDecree.toString());

            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "AppointmentDecree Saved successfully");
            return "redirect:/appointmentDecree";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/appointmentDecree";
        }
    }

    @PutMapping("/edit")
    public String edit(@Valid AppointmentDecree appointmentDecree, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {

                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error(" Did Not Edit ! --->" + result.getAllErrors());
                return "appointmentDecree";

            }
            service.update(appointmentDecree);
            log.info("AppointmentDecree Edited - Put Method ---->" + "appointmentDecree :" + appointmentDecree.toString());

            model.addAttribute("appointmentDecree", appointmentDecree);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "AppointmentDecree Edited Successfully .");
            return "redirect:/appointmentDecree";

        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/appointmentDecree";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            service.logicalRemove(id);
            log.info("The Person With Id :" + id + " Successfully Deleted");
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "AppointmentDecree With Id : " + id + " Successfully Deleted .");
            return "redirect:/appointmentDecree";
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
            List<AppointmentDecree> appointmentDecreeList = service.findAppointmentDecreeByDeletedFalse();
            log.info("Controller - Find AppointmentDecree With Deleted False - Get Method");
            model.addAttribute("appointmentDecree", new AppointmentDecree());
            model.addAttribute("appointmentDecreeList", appointmentDecreeList);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "AppointmentDecree List Is Not Empty");
            List<Person> person = personService.findAll();
            model.addAttribute("person", person);
            return "appointmentDecree";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<AppointmentDecree>> findById(@PathVariable("id") Long id,Model model) {
        try {

            Optional<AppointmentDecree> appointmentDecree = service.findAppointmentDecreeByIdAndDeletedFalse(id);
            return ResponseEntity.ok(appointmentDecree);
        }catch (Exception e) {

            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return null;
        }
    }
}
