package com.home.SpringBootAutomation.controller;


import com.home.SpringBootAutomation.model.Skills;
import com.home.SpringBootAutomation.service.SkillsService;
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
@Slf4j
@RequestMapping("/skills")
public class SkillsController {

    private final SkillsService service;

    public SkillsController(SkillsService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public String save(@Valid Skills skills, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error(" Did Not Saved ! --->" + result.getAllErrors());
                return "skills";

            }
            service.save(skills);
            log.info("Skill Saved - Post Method ---->" + "skill :" + skills.toString());

            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Skill Saved successfully");
            return "redirect:/skills";


        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/skills";
        }

    }

    @PutMapping("/edit")
    public String edit(@Valid Skills skills, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {

                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error(" Did Not Edit ! --->" + result.getAllErrors());
                return "skills";

            }
            service.update(skills);
            log.info("Skill Edited - Put Method ---->" + "skill :" + skills.toString());

            model.addAttribute("skills", skills);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Skill Edited Successfully .");
            return "redirect:/skills";


        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/skills";
        }
    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {

            service.logicalRemove(id);
            log.info("The Skill With Id :" + id + " Successfully Deleted");
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Skill With Id : " + id + " Successfully Deleted .");
            return "redirect:/skills";


        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/skills";
        }
    }

    @GetMapping
    public String findAll(Model model) {

        try {
            List<Skills> skillsList = service.findSkillsByDeletedFalse();
            log.info("Find Skills With Deleted False - Get Method");

            model.addAttribute("skills", new Skills());
            model.addAttribute("skillsList", skillsList);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Skills List Is Not Empty");

            return "skills";

        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/skills";
        }
    }


    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<Skills>> findById(@PathVariable("id") Long id, Model model){
        try {

            Optional<Skills> skills = service.findSkillsByIdAndDeletedFalse(id);
            return ResponseEntity.ok(skills);
        }catch (Exception e) {

            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
//            return ResponseEntity.noContent();
            return null;
        }
    }






}
