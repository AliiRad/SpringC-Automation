package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.Model.AppointmentDecree;
import com.home.SpringBootAutomation.service.AppointmentDecreeServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping(value = "/appointmentDecree")
public class AppointmentDecreeController {
    private AppointmentDecreeServiceImp serviceImp;

    public AppointmentDecreeController(AppointmentDecreeServiceImp serviceImp) {
        this.serviceImp = serviceImp;
    }

    @GetMapping
    public String showAppointmentDecree(Model model) {
        log.info("Controller-AppointmentDecree-Get-FindAll");
        model.addAttribute("appointmentDecree", new AppointmentDecree());
        model.addAttribute("appointmentDecreeList", serviceImp.findAll());
        return "appointmentDecree";
    }

    @PostMapping(value = "/save")
    public String saveAppointmentDecree(AppointmentDecree appointmentDecree) {
        log.info("Controller-AppointmentDecree-Save: " + appointmentDecree.toString());
        serviceImp.save(appointmentDecree);
        return "redirect:/appointmentDecree";
    }

    @GetMapping(value ="/id/{id}")
    public String showAppointmentDecree(@PathVariable("id") Long id , Model model){
        AppointmentDecree appointmentDecree = serviceImp.findById(id);
        if (appointmentDecree != null){
            model.addAttribute("appointmentDecree", appointmentDecree);
            return "appointmentDecree";
        }else {
            return "redirect:/appointmentDecree";
        }
    }

    @PostMapping(value ="/edit")
    public String editAppointmentDecree(AppointmentDecree appointmentDecree) {
        serviceImp.edit(appointmentDecree);
        return "appointmentDecree";
    }

    @PostMapping(value ="/delete")
    public String deleteAppointmentDecree(Long id) {
        log.info("Controller-AppointmentDecree-Delete: " + id);
        serviceImp.logicalRemove(id);
        return "redirect:/appointmentDecree";
    }
}
