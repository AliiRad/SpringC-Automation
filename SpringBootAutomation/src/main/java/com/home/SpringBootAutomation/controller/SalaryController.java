package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.Model.Salary;
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
    public String showSalaryList(Model model){
        log.info("Salary Table - Get");
        try {
//            for th:object="${salary}"
            model.addAttribute("salary", new Salary());
            model.addAttribute("salaryList", salaryService.findAllByDeletedFalse());
            return "salaryTable";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //salary form
    @GetMapping("/salaryForm")
    public String showForm(Model model) {
        log.info("Salary Form - Get");
        try {
            //for th:object="${salary}"
            model.addAttribute("salary", new Salary());
            return "salaryForm";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
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
            log.info(salary.toString());
            model.addAttribute("salary", new Salary());
            model.addAttribute("msg", "Salary Saved");
            return "redirect:/salary/salaryTable";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //salary edit form
    @GetMapping(value = "/edit")
    public String edit(@RequestParam Long id, Model model) {
        log.info("Salary - Edit Page");
        try {
            Optional<Salary> salary = salaryService.findById(id);
            model.addAttribute("salary",salary);
            return "salaryForm";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //salary edit
    @PostMapping(value = "/edit")
    public String editForm(@Valid Salary salary, Model model){
        log.info("Salary - Edit");
        try {
            Long id = salary.getId();
            Optional<Salary> salary1 = salaryService.findById(id);
            if (salary1.isPresent()){
                salaryService.edit(salary);
                log.info("Salary Edited");
                model.addAttribute("msg", "Salary Edited");
                return "redirect:/salary/salaryTable";
            }
            return "salaryForm";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //salary logical remove
    @PostMapping("/delete")
    public String editForm(@ModelAttribute("id") Long id, Model model){
        log.info("Salary - Delete");
        try {
            Optional<Salary> salary = salaryService.findById(id);
            if (salary.isPresent()){
                salaryService.logicalRemove(id);
                log.info("Salary Removed");
                model.addAttribute("msg", "Salary Removed");
                return "redirect:/salary/salaryTable";
            }
            return "salaryForm";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //salary year search form
    @GetMapping(value = "/findByYear")
    public String searchByYear(@ModelAttribute("year") String year, Model model) {
        log.info("Salary - Search By Year Page");
        try {
            model.addAttribute("salary",new Salary());
            Optional<Salary> salary = salaryService.findByYear(Integer.valueOf(year));
            if (salary.isPresent()){
                model.addAttribute("salaryYear",salary.get());
                System.out.println(year);
                System.out.println(salaryService.findByYear(Integer.valueOf(year)));
                return "forward:/salary/salaryTable";
            }
            return "redirect:/salary/salaryTable";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //todo unique key violated when saving after setting deleted true
    //todo th tag has warning in html files

}
