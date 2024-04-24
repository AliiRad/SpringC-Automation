package com.home.SpringBootAutomation.controller;


import com.home.SpringBootAutomation.model.Salary;
import com.home.SpringBootAutomation.service.impl.SalaryServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/salary")
public class SalaryController {

    private final SalaryServiceImpl salaryService;

    public SalaryController(SalaryServiceImpl salaryService) {
        this.salaryService = salaryService;
    }

    //salary table
    @GetMapping("/salaryTable")
    public String showSalaryList(@ModelAttribute("year") String year,Model model){
        log.info("Salary Table - Get");
        try {
            model.addAttribute("salary", new Salary());
            if (year.isEmpty()){
                model.addAttribute("salaryList", salaryService.findAllByDeletedFalse());
            }else {
                Optional<Salary> salary = salaryService.findByYear(Integer.valueOf(year));
                if (salary.isPresent()){
                    model.addAttribute("salaryList",salary.get());
                }else {
                    model.addAttribute("msg", "No salary available");
                }
            }
            return "salaryTable";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //salary form
    @GetMapping("/salaryForm")
    public String showSaveForm(Model model) {
        log.info("Salary Form - Get");
        try {
            //for th:object="${salary}"
            model.addAttribute("salary", new Salary());
            return "salaryForm";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "errorPage";
        }
    }

    //salary save
    @PostMapping(value = "/save")
    public String save(@Valid Salary salary, BindingResult result, Model model){
        log.info("Salary Save - Post");
        if (result.hasErrors()){
            log.error(result.getAllErrors().toString());
            return "salaryForm";
        }
        try {
            salaryService.save(salary);
            log.info("Salary Saved");
            model.addAttribute("salary", new Salary());
            model.addAttribute("msg", "Salary Saved");
            return "salaryForm";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "errorPage";
        }
    }

    //salary edit form
    @GetMapping(value = "/edit")
    public String showEditForm(@RequestParam Long id, Model model) {
        log.info("Salary - Edit Page");
        try {
            Optional<Salary> salary = salaryService.findById(id);
            model.addAttribute("salary",salary);
            return "salaryEdit";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "errorPage";
        }
    }

    //todo why /salary/save works instead of this
    //salary edit
    @PostMapping(value = "/edit")
    public String edit(@Valid Salary salary, Model model){
        log.info("Salary - Edit");
        try {
            Long id = salary.getId();
            Optional<Salary> salary1 = salaryService.findById(id);
            if (salary1.isPresent()){
                salaryService.edit(salary);
                log.info("Salary Edited");
                model.addAttribute("msg", "Salary Edited");
                return "salaryEdit";
            }
            return "salaryEdit";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "errorPage";
        }
    }

    //todo I cant show the error msg it gives 500 error
    //salary logical remove
    @PostMapping("/delete")
    public String softDelete(@ModelAttribute("id") Long id,@ModelAttribute("year") Integer year, Model model){
        log.info("Salary - Delete");
        try {
            Optional<Salary> salary = salaryService.findById(id);
            if (salary.isPresent()){
                salary.get().setYear(Integer.valueOf(year + id.toString()));
                salaryService.save(salary.get());
                salaryService.logicalRemove(id);
                log.info("Salary Removed");
                model.addAttribute("msg", "Salary Removed");
                return "salaryTable";
            }
            return "salaryTable";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "errorPage";
        }
    }

}
