package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.service.PersonService;
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
@RequestMapping("/person")
@Slf4j
public class PersonController {


    //    -------------------------------------------------------------------------
    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }
    //    -------------------------------------------------------------------------

    @PostMapping("/save")
    public String save(@Valid Person person, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                log.error(result.getAllErrors().toString());

                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                return "persons";
            } else {
                service.save(person);
                log.info("Person Saved - Post Method");
                log.info(person.toString());

                model.addAttribute("messageType", "success");
                model.addAttribute("messageContent", "Person Saved successfully");
                return "redirect:/persons";
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
    public String edite(Long id, @Valid Person person, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                log.error(result.getAllErrors().toString());

                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                return "persons";

            } else if (service.findPersonByIdAndDeletedFalse(id).isPresent()) {
                service.update(id, person);
                log.info("Person Edited - Put Method");
                log.info(person.toString());

                model.addAttribute("person", person);
                model.addAttribute("messageType", "success");
                model.addAttribute("messageContent", "Person Edited Successfully .");
                return "redirect:/persons";

            } else {
                log.error("Person Not Found !");

                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", "Person With Id :" + id + "  Not Found !");
                return "redirect:/persons";
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
            if (service.findPersonByIdAndDeletedFalse(id).isPresent()) {
                service.logicalRemove(id);

                log.info("Person Deleted - Put Method");
                log.info("The Person With Id :" + id + "Successfully Deleted");
                model.addAttribute("messageType", "success");
                model.addAttribute("messageContent", "Person With Id : " + id + " Successfully Deleted .");
                return "redirect:/persons";
            } else {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", "Person With Id :" + id + "  Not Found !");
                return "redirect:/persons";
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());
            return "error-page";
            //TODO: Maybe a 505 error page ?
        }
    }
    //    -------------------------------------------------------------------------

    @GetMapping
    public String findAll(Model model) {
        try {
            if (service.countByDeletedFalse() > 0) {
                List<Person> personList = service.findPersonByDeletedFalse();
                log.info("Find Persons With Deleted False - Get Method");

                model.addAttribute("person", new Person());
                model.addAttribute("personList", personList);
                model.addAttribute("messageType", "success");
                model.addAttribute("messageContent", "Person List Is Not Empty");

                return "persons";

            } else {

                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", "Person List Is Empty");

                return "persons";

            }
        } catch (Exception e) {

            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());

            return "error-page";
        }
    }

    //    -------------------------------------------------------------------------
    @GetMapping("/findById/{id}")
    public String findById(@PathVariable("id") Long id , Model model){
        try {
            Optional<Person> person = service.findPersonByIdAndDeletedFalse(id);
            log.info("Find Person By Id And Deleted False - Get Method");

            if (person.isPresent()){
                log.info("Active Person With Id : "+id+" Was Founded");

                model.addAttribute("person" , person);
                model.addAttribute("messageType", "success");
                model.addAttribute("messageContent" , "Active Person With Id : "+id+" Was Found" );
                return "persons";
            }else {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", "Active Person With Id : "+id+" Was Not Found");
                return "Persons";

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
