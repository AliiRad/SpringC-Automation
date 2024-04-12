package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.Model.Jobs;
import com.home.SpringBootAutomation.service.JobsService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/jobs")
public class JobsController {
    @Autowired
    private JobsService service;
    //    -------------------------------------------------------------------------
    @PostMapping("/save")
    public String save(@Valid Jobs jobs , BindingResult result , Model model){
        if (result.hasErrors()){
            log.error(result.getAllErrors().toString());
            return "jobs";
        }
        log.info("Job Saved - Post Method");
        log.info(jobs.toString());
        service.save(jobs);
        return "redirect:/jobs";

    }
    //    -------------------------------------------------------------------------



    @PostMapping("/edit")
    public String edit(Long id , @Valid Jobs jobs , Model model){
        log.info("Job Edited - Post Method");
        if (service.findById(id) != null){
            service.update(id , jobs);

            model.addAttribute("jobs" , jobs);
            model.addAttribute("message" , "Job Edited Successfully !");

            return "redirect:/jobs";
        }

        model.addAttribute("message" ,"Job Not Found !");
        return "redirect:/jobs";
    }
    //    -------------------------------------------------------------------------

    @PostMapping("/delete")
    public String delete(Long id , Model model){
        log.info("Job Deleted - Post Method");
        service.logicalRemove(id);
        model.addAttribute("message" , "Job Deleted !");
        return "redirect:/jobs";

    }

    //    -------------------------------------------------------------------------



    @GetMapping
    public String findAll(Model model){

        log.info("Job Founded - Get Method");
        model.addAttribute("jobs" , new Jobs());
        model.addAttribute("jobsList" , service.findAll());
        return "jobs";

    }

    //    -------------------------------------------------------------------------

    @GetMapping("/findById/{id}")
    public String findById(@PathVariable("id") Long id , Model model){

        log.info("Find By Id - Get Method");


        Jobs jobs = service.findById(id);
        model.addAttribute("jobs" , jobs);
        return "jobs";
    }

    //    -------------------------------------------------------------------------

    @GetMapping("/findByCompanyName/{companyName}")
    public String findByCompanyName(@PathVariable("companyName") String companyName , Model model){

        log.info("Find By Company Name - Get Method");


        model.addAttribute("jobs" ,new Jobs());
        model.addAttribute("jobsList" , service.findByCompanyName(companyName));
        return "jobs";
    }

    //    -------------------------------------------------------------------------


    @GetMapping("/findByAddress/{address}")
    public String findByAddress(@PathVariable("address") String address , Model model){

        log.info("Find By Address - Get Method");


        model.addAttribute("jobs" , new Jobs());
        model.addAttribute("jobsList" , service.findByAddress(address));
        return "jobs";

    }

    //    -------------------------------------------------------------------------

    @GetMapping("/findALlByPagination")
    public String findJobsByPagination(@RequestParam(defaultValue = "1") Integer pageNo,
                                       @RequestParam(defaultValue = "5") Integer pageSize,
                                       Model model){

        log.info("Find By Pagination - Get Method");



        model.addAttribute("jobs" , service.getJobsByPagination(pageNo , pageSize));
        model.addAttribute("totalPages" , ((int)(service.getJobsCount()/pageSize)) + 1);
        model.addAttribute("currentPage" , pageNo);
        return "skills";
    }
    //    -------------------------------------------------------------------------
}
