package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Salary;
import com.home.SpringBootAutomation.service.impl.SalaryServiceImpl;
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
@RequestMapping("/salary")
@Slf4j
public class SalaryController {

    private final SalaryServiceImpl service;

    public SalaryController(SalaryServiceImpl service) {
        this.service = service;
    }

    @PostMapping(value = "/save")
    public String save(@Valid Salary salary, Model model, BindingResult result){
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error("Controller - Salary Save Failed ! --->" + result.getAllErrors());
                return "salary";

            }
            service.save(salary);
            log.info("Controller - Salary Saved - Post Method ---->" + " salary :" + salary.toString());

            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Salary Saved successfully");
            return "redirect:/salary";


        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/salary";
        }
    }

    @PostMapping("/edit") //TODO: PutMapping
    public String edit(@Valid Salary salary, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error("Controller - Salary Edit Failed ! --->" + result.getAllErrors());
                return "person";

            }
            service.update(salary);
            log.info("Controller - Salary Edited - Put Method ---->" + " person :" + salary.toString());

            model.addAttribute("salary", salary);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Salary Saved successfully");
            return "redirect:/salary";

        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/salary";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            service.logicalRemove(id);

            log.info("The Salary With Id :" + id + " Successfully Deleted");
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Salary With Id : " + id + " Successfully Deleted .");
            return "redirect:/salary";

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
            List<Salary> salaryList = service.findSalaryByDeletedFalse();
            log.info("Controller - Find Salaries With Deleted False - Get Method");

            model.addAttribute("salary", new Salary());
            model.addAttribute("salaryList", salaryList);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Salary List Is Not Empty");

            return "salary";

        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());

            return "error-page";
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<Salary>> findById(@PathVariable("id") Long id, Model model) {
        try {
            Optional<Salary> salary = service.findSalaryByIdAndDeletedFalse(id);

            return ResponseEntity.ok(salary);
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
