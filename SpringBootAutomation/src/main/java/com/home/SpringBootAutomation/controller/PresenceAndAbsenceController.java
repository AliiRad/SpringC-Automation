package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.PresenceAndAbsence;
import com.home.SpringBootAutomation.service.impl.PresenceAndAbsenceServiceImpl;
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
@RequestMapping("/presenceAndAbsence")
@Slf4j
public class PresenceAndAbsenceController {

    private final PresenceAndAbsenceServiceImpl service;

    public PresenceAndAbsenceController(PresenceAndAbsenceServiceImpl service) {
        this.service = service;
    }

    //todo : employee hasn't been set yet
    @PostMapping(value = "/save")
    public String save(@Valid PresenceAndAbsence presenceAndAbsence, Model model, BindingResult result){
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error("Controller - PresenceAndAbsence Save Failed ! --->" + result.getAllErrors());
                return "presenceAndAbsence";

            }
            service.save(presenceAndAbsence);
            log.info("Controller - PresenceAndAbsence Saved - Post Method ---->" + " presenceAndAbsence :" + presenceAndAbsence.toString());

            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "PresenceAndAbsence Saved successfully");
            return "redirect:/presenceAndAbsence";


        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/presenceAndAbsence";
        }
    }

    @PostMapping("/edit") //TODO: PutMapping
    public String edit(@Valid PresenceAndAbsence presenceAndAbsence, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error("Controller - PresenceAndAbsence Edit Failed ! --->" + result.getAllErrors());
                return "presenceAndAbsence";

            }
            service.update(presenceAndAbsence);
            log.info("Controller - PresenceAndAbsence Edited - Put Method ---->" + " presenceAndAbsence :" + presenceAndAbsence.toString());

            model.addAttribute("presenceAndAbsence", presenceAndAbsence);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "PresenceAndAbsence Saved successfully");
            return "redirect:/presenceAndAbsence";

        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/presenceAndAbsence";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            service.logicalRemove(id);

            log.info("The PresenceAndAbsence With Id :" + id + " Successfully Deleted");
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "PresenceAndAbsence With Id : " + id + " Successfully Deleted .");
            return "redirect:/presenceAndAbsence";

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
            List<PresenceAndAbsence> presenceAndAbsenceList = service.findPresenceAndAbsenceByDeletedFalse();
            log.info("Controller - Find PresenceAndAbsence With Deleted False - Get Method");

            model.addAttribute("presenceAndAbsence", new PresenceAndAbsence());
            model.addAttribute("presenceAndAbsenceList", presenceAndAbsenceList);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "PresenceAndAbsence List Is Not Empty");

            return "presenceAndAbsence";

        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());

            return "error-page";
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<PresenceAndAbsence>> findById(@PathVariable("id") Long id, Model model) {
        try {
            Optional<PresenceAndAbsence> presenceAndAbsence = service.findPresenceAndAbsenceByIdAndDeletedFalse(id);

            return ResponseEntity.ok(presenceAndAbsence);
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
