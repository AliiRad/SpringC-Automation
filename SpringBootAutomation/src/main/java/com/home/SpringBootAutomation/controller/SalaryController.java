package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Salary;
import com.home.SpringBootAutomation.service.impl.SalaryServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/salary")
@Slf4j
public class SalaryController {

    private final SalaryServiceImpl salaryService;

    public SalaryController(SalaryServiceImpl salaryService) {
        this.salaryService = salaryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String salaryForm(Model model) {
        model.addAttribute("salaryList", salaryService.findSalaryByDeletedFalse());
        model.addAttribute("salary", new Salary());
        return "salary";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Salary save(Model model, @Valid Salary salary, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        return salaryService.save(salary);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public Salary edit(Model model, @Valid Salary salary, BindingResult bindingResult) throws NoContentException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        return salaryService.update(salary);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Salary remove(Model model, @PathVariable Long id) throws NoContentException {
        Salary salary = salaryService.findById(id);
        salary.setYear(Integer.valueOf(salary.getYear() + id.toString()));
        salaryService.update(salary);

        return salaryService.logicalRemove(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Salary findById(Model model, @PathVariable Long id) throws NoContentException {
        return salaryService.findById(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/year/{year}", method = RequestMethod.GET)
    public Salary findByYear(Model model, @PathVariable Integer year) throws NoContentException {
        return salaryService.findSalaryByYearAndDeletedFalse(year);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Salary> findAll(Model model) throws NoContentException {
        return salaryService.findAll();
    }


}
