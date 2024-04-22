package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Jobs;
import com.home.SpringBootAutomation.service.JobsService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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


    //    -------------------------------------------------------------------------
    private final JobsService service;

    @Autowired
    public JobsController(JobsService service) {
        this.service = service;
    }
    //    -------------------------------------------------------------------------


    @PostMapping("/save")
    public String save(@Valid Jobs jobs, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                log.error(result.getAllErrors().toString());

                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                return "jobs";
            } else {
                service.save(jobs);
                log.info("Job Saved - Post Method");
                log.info(jobs.toString());

                model.addAttribute("messageType", "success");
                model.addAttribute("messageContent", "Job Saved successfully");
                return "redirect:/jobs";
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "error-page";
        }
    }

    //    -------------------------------------------------------------------------

    @PutMapping("/edit")
    public String edit(Long id, @Valid Jobs jobs, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                log.error(result.getAllErrors().toString());

                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                return "jobs";

            } else if (service.findJobsByIdAndDeletedFalse(id).isPresent()) {
                service.update(id, jobs);
                log.info("Job Edited - Put Method");
                log.info(jobs.toString());

                model.addAttribute("jobs", jobs);
                model.addAttribute("messageType", "success");
                model.addAttribute("messageContent", "Job Edited Successfully .");
                return "redirect:/jobs";

            } else {
                log.error("Job Not Found !");

                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", "Job With Id :" + id + "  Not Found !");
                return "redirect:/jobs";
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "error-page";
        }

    }

    //    -------------------------------------------------------------------------

    @DeleteMapping("/delete")
    public String delete(Long id, Model model) {
        try {
            //TODO: Using logicalRemoveWithReturn to return deleted object .
            if (service.findJobsByIdAndDeletedFalse(id).isPresent()) {
                service.logicalRemove(id);

                log.info("Job Deleted - Put Method");
                log.info("The Job With Id :" + id + "Successfully Deleted");
                model.addAttribute("messageType", "success");
                model.addAttribute("messageContent", "Job With Id : " + id + " Successfully Deleted .");
                return "redirect:/jobs";

            } else {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", "Job With Id :" + id + "  Not Found !");
                return "redirect:/jobs";

            }


        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "error-page";
        }
    }

    //    -------------------------------------------------------------------------
    @GetMapping
    public String findAll(Model model){
        try {

            if (service.countByDeletedFalse() >0){
                List<Jobs> jobsList =  service.findJobsByDeletedFalse();
                log.info("Find Jobs With Deleted False - Get Method");


                //TODO: is it needed?
                model.addAttribute("jobs", new Jobs());
                model.addAttribute("jobsList", jobsList);
                model.addAttribute("messageType", "success");
                model.addAttribute("messageContent", "Jobs List Is Not Empty");

                return "jobs";

            }else {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", "Job List Is Empty");
                return "jobs";

            }

        }catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "error-page";
        }
    }

    //    -------------------------------------------------------------------------
    @GetMapping("/findById/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        try {

            Optional<Jobs> jobs = service.findJobsByIdAndDeletedFalse(id);
            log.info("Find Job By Id And Deleted False - Get Method");

            if (jobs.isPresent()){
                log.info("Active Job With Id : "+id+" Was Founded");
                model.addAttribute("jobs", jobs);
                model.addAttribute("messageType", "success");
                model.addAttribute("messageContent" , "Active Job With Id : "+id+" Was Found" );
                return "jobs";


            }else {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", "Active Job With Id : "+id+" Was Not Found");
                return "jobs";

            }

        }catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "error-page";
        }
    }

    //    -------------------------------------------------------------------------
}
