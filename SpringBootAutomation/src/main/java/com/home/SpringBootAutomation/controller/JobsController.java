package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Jobs;
import com.home.SpringBootAutomation.service.JobsService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@Controller
@Slf4j
@RequestMapping("/jobs")

public class JobsController {


    private final JobsService service;


    public JobsController(JobsService service) {
        this.service = service;
    }


    @PostMapping("/save")
    public String save(@Valid Jobs jobs, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                log.error(result.getAllErrors().toString());

                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error(" Did Not Saved ! --->" + result.getAllErrors());
                return "jobs";
            }
            service.save(jobs);
            log.info("Job Saved - Post Method ---->" + "job :" + jobs.toString());

            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Job Saved successfully");
            return "redirect:/jobs";

        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/jobs";
        }
    }


    @PutMapping("/edit")
    public String edit( @Valid Jobs jobs, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {

                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error(" Did Not Edit ! --->" + result.getAllErrors());
                return "jobs";

            }
            service.update(jobs);
            log.info("Job Edited - Put Method ---->" + "job :" + jobs.toString());

            model.addAttribute("jobs", jobs);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Job Edited Successfully .");
            return "redirect:/jobs";



        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/jobs";
        }

    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {

            service.logicalRemove(id);
            log.info("The Job With Id :" + id + " Successfully Deleted");
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Job With Id : " + id + " Successfully Deleted .");
            return "redirect:/jobs";




        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/jobs";
        }
    }

    @GetMapping
    public String findAll(Model model){
        try {


            List<Jobs> jobsList = service.findJobsByDeletedFalse();
            log.info("Find Jobs With Deleted False - Get Method");

            model.addAttribute("jobs", new Jobs());
            model.addAttribute("jobsList", jobsList);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Jobs List Is Not Empty");

            return "jobs";


        }catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/jobs";
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<Jobs>> findById(@PathVariable("id") Long id, Model model){
        try {

            Optional<Jobs> jobs = service.findJobsByIdAndDeletedFalse(id);
            return ResponseEntity.ok(jobs);
        }catch (Exception e) {

            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
//            return ResponseEntity.noContent();
            return null;
        }
    }





}
