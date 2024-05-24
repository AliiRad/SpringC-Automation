package com.home.SpringBootAutomation.controller;

import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Section;
import com.home.SpringBootAutomation.service.impl.SectionServiceImp;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j
@Controller
@RequestMapping("/section")
public class SectionController {

    private SectionServiceImp service;

    public SectionController(SectionServiceImp service) {
        this.service = service;
    }

    @GetMapping
    public String showSection(Model model) {

        if (service.findAll().isEmpty()) {
            return "section";
        } else {
            //TODO: Return JSON Instead of String
            return service.findAll().toString();
        }
    }

    @PostMapping
    @ResponseBody
    public Section saveSection(Section Section, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("Controller-Section-Post-Save-Error: " + Section.toString());
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()).collect(Collectors.toList()).toString());
        }
        log.info("Controller-Section-Post-Save");
        return service.save(Section);
    }

    @PutMapping
    @ResponseBody
    public Section editSection(Section Section, BindingResult bindingResult) throws NoContentException {
        if (bindingResult.hasErrors()) {
            log.error("Controller-Section-Put-Edit-Error: " + Section.toString());
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()).collect(Collectors.toList()).toString());
        }
        log.info("Controller-Section-Post-Edit");
        return service.edit(Section);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Section deleteSection(@PathVariable Long id) throws NoContentException {
        log.info("Controller-Section-Delete");
        return service.logicalRemove(id);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Section showSectionById(@PathVariable Long id) throws NoContentException {
        log.info("Controller-Section-Get-FindById");
        return service.findById(id);
    }

}
