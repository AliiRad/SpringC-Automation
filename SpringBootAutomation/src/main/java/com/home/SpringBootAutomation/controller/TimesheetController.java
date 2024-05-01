package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Timesheet;
import com.home.SpringBootAutomation.service.impl.TimesheetServiceImpl;
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
@RequestMapping("/timesheet")
@Slf4j
public class TimesheetController {

    private final TimesheetServiceImpl service;

    public TimesheetController(TimesheetServiceImpl service) {
        this.service = service;
    }

    //todo : employee and manager hasn't been set yet
    //todo : employee and manager signatures haven't been set yet
    @PostMapping(value = "/save")
    public String save(@Valid Timesheet timesheet, Model model, BindingResult result){
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error("Controller - Timesheet Save Failed ! --->" + result.getAllErrors());
                return "timesheet";

            }
            service.save(timesheet);
            log.info("Controller - Timesheet Saved - Post Method ---->" + " timesheet :" + timesheet.toString());

            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Timesheet Saved successfully");
            return "redirect:/timesheet";


        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/timesheet";
        }
    }

    @PostMapping("/edit") //TODO: PutMapping
    public String edit(@Valid Timesheet timesheet, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error("Controller - Timesheet Edit Failed ! --->" + result.getAllErrors());
                return "timesheet";

            }
            service.update(timesheet);
            log.info("Controller - Timesheet Edited - Put Method ---->" + " timesheet :" + timesheet.toString());

            model.addAttribute("timesheet", timesheet);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Timesheet Saved successfully");
            return "redirect:/timesheet";

        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/timesheet";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            service.logicalRemove(id);

            log.info("The Timesheet With Id :" + id + " Successfully Deleted");
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Timesheet With Id : " + id + " Successfully Deleted .");
            return "redirect:/timesheet";

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
            List<Timesheet> timesheetList = service.findTimesheetByDeletedFalse();
            log.info("Controller - Find Timesheet With Deleted False - Get Method");

            model.addAttribute("timesheet", new Timesheet());
            model.addAttribute("timesheetList", timesheetList);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Timesheet List Is Not Empty");

            return "timesheet";

        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());

            return "error-page";
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<Timesheet>> findById(@PathVariable("id") Long id, Model model) {
        try {
            Optional<Timesheet> timesheet = service.findTimesheetByIdAndDeletedFalse(id);

            return ResponseEntity.ok(timesheet);
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
