package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.service.PersonService;
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
@RequestMapping("/person")
@Slf4j
public class PersonController {


    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public String save(@Valid Person person, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error("Controller - Person Save Failed ! --->" + result.getAllErrors());
                return "person";

            }
            service.save(person);
            log.info("Controller - Person Saved - Post Method ---->" + " person :" + person.toString());

            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Person Saved successfully");
            return "redirect:/person";


        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/person";
        }
    }



    @PostMapping("/edit") //TODO: PutMapping
    public String edit(@Valid Person person, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error("Controller - Person Edit Failed ! --->" + result.getAllErrors());
                return "person";

            }
            service.update(person);
            log.info("Controller - Person Edited - Post Method ---->" + " person :" + person.toString());

            model.addAttribute("person", person);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Person Saved successfully");
            return "redirect:/person";

        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "redirect:/person";


        }
    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
//
            service.logicalRemove(id);

            log.info("The Person With Id :" + id + " Successfully Deleted");
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Person With Id : " + id + " Successfully Deleted .");
            return "redirect:/person";

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

            List<Person> personList = service.findPersonByDeletedFalse();
            log.info("Controller - Find Persons With Deleted False - Get Method");

            model.addAttribute("person", new Person());
            model.addAttribute("personList", personList);
            model.addAttribute("messageType", "success");
            model.addAttribute("messageContent", "Person List Is Not Empty");

            return "person";


        } catch (Exception e) {

            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());

            return "error-page";
        }
    }


    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<Person>> findById(@PathVariable("id") Long id, Model model){
        try {


            Optional<Person> person = service.findPersonByIdAndDeletedFalse(id);

            return ResponseEntity.ok(person);
        } catch (Exception e) {

            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
//TODO: return Error Page .

//            return ResponseEntity.noContent();
            return null;
        }

    }


}
