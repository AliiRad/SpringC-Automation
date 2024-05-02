package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.AppointmentDecree;
import com.home.SpringBootAutomation.service.impl.AppointmentDecreeServiceImp;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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
    private AppointmentDecreeServiceImp service;

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

    @PostMapping("/edit")
    public String edit(@Valid AppointmentDecree appointmentDecree, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error("Controller - AppointmentDecree Edit Failed ! --->" + result.getAllErrors());
                return "appointmentDecree";
            }
            service.update(appointmentDecree);
            log.info("Controller - AppointmentDecree Edited - Put Method ---->" + " appointmentDecree :" + appointmentDecree.toString());

            model.addAttribute("appointmentDecree", appointmentDecree);
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

    @GetMapping(value = "/delete/{id}")
    public String deleteAppointmentDecree(@PathVariable("id") long id) throws NoContentException {
        Optional<AppointmentDecree> appointmentDecree = service.findAppointmentDecreeByIdAndDeletedFalse(id);
        if (appointmentDecree.isPresent()) {
            service.logicalRemove(id);
            return "redirect:/appointmentDecree";
        }
        return "Invalid Id";
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
            return "appointmentDecree";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<AppointmentDecree>> findById(@PathVariable("id") Long id, Model model) {
        try {
            Optional<AppointmentDecree> appointmentDecree = service.findAppointmentDecreeByIdAndDeletedFalse(id);
            return ResponseEntity.ok(appointmentDecree);
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());

            //TODO: return Error Page .

            //return ResponseEntity.noContent();
            return null;
        }

    }

}
