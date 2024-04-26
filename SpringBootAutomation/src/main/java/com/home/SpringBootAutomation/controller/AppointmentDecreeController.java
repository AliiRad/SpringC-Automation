package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.AppointmentDecree;
import com.home.SpringBootAutomation.service.impl.AppointmentDecreeServiceImp;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping
public class AppointmentDecreeController {
    private AppointmentDecreeServiceImp serviceImp;

    public AppointmentDecreeController(AppointmentDecreeServiceImp serviceImp) {
        this.serviceImp = serviceImp;
    }

//    @GetMapping(value = "/appointmentDecree")
//    public String showAppointmentDecreeList(Model model) {
//        model.addAttribute("appointmentDecree",new AppointmentDecree());
//        model.addAttribute("appointmentDecreeList", serviceImp.findAll());
//        return "appointmentDecree";
//    }
//
//    @PostMapping(value = "/appointmentDecree/save")
//    public String saveAppointmentDecree(AppointmentDecree appointmentDecree) {
//            serviceImp.save(appointmentDecree);
//            return "redirect:/appointmentDecree";
//    }
//
//    @GetMapping(value = "/edit/{id}")
//    public String showEditPage(@PathVariable("id") Long id, Model model) {
//        AppointmentDecree appointmentDecree = serviceImp.findById(id);
//        if (appointmentDecree != null) {
//            model.addAttribute("appointmentDecreeEdit", appointmentDecree);
//            return "appointmentDecreeEdit";
//        }
//        return "Invalid Id";
//    }
//
//    @PostMapping(value = "/update/{id}")
//    public String editForm(@PathVariable("id") Long id, @Valid AppointmentDecree appointmentDecree) {
//        serviceImp.save(appointmentDecree);
//        return "redirect:/appointmentDecree";
//    }
//
//    @GetMapping(value = "/delete/{id}")
//    public String deleteAppointmentDecree(@PathVariable("id") long id) {
//        AppointmentDecree appointmentDecree = serviceImp.findById(id);
//        if (appointmentDecree!=null) {
//            serviceImp.remove(appointmentDecree);
//            return "redirect:/appointmentDecree";
//        }
//        return "Invalid Id";
//    }
}
