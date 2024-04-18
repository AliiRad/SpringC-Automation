package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.service.impl.PersonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(value = "/person")
public class PersonController {
    private PersonServiceImpl personService;

    public PersonController(PersonServiceImpl personService) {
        this.personService = personService;
    }
    @GetMapping
    public String showPerson(Model model){
        log.info("Controller-PersonController-Get-FindAll");
        model.addAttribute("person",new Person());
        model.addAttribute("personList",personService.findAll());
        return "/person";
    }
    @GetMapping(value = "/id{id}")
    public String showPersonFindById(@ModelAttribute("id")Long id){
        log.info("Controller-PersonController-Get-FindById");
        Optional<Person>person=Optional.ofNullable(personService.findById(id));
        if (person.isPresent()){
            return "/person";
        }else {
            return "error-404";
        }
    }
    @GetMapping(value = "/nationalId")
    public String showPersonFindByNationalId(Model model,@ModelAttribute("nationalId")String nationalId){
        log.info("Controller-PersonController-Get-FindByNationalId");
        List<Person> personList =personService.findByNationalId(nationalId);
        if (!personList.isEmpty()){
            model.addAttribute("personList", personList);
            return "/nationalId";
        }else {
            return "error-404";
        }
    }
    @GetMapping(value = "/date")
    public String showPersonFindByBirthDate(Model model, @ModelAttribute("timeStamp") LocalDate birthdate){
        log.info("Controller-PersonController-Get-FindByBirthDate");
        List<Person> personList =personService.findByBirthDate(birthdate);
        if (!personList.isEmpty()){
            model.addAttribute("personList", personList);
            return "person";
        }else {
            return "error-404";
        }
    }
    @PostMapping(value = "/save")
    public String savePerson(Person person){
        log.info("Controller-PersonController-Post-SavePerson"+ person.toString());
        log.info("Controller-PersonController-Post-SavePerson");
        personService.save(person);
        return "redirect:/Person";
    }
    @PostMapping(value = "/edit")
    public String editPerson(Person person){
        log.info("Controller-PersonController-Post-EditPerson");
        personService.edit(person);
        return "person";
    }
    @PostMapping(value = "/delete")
    public String personDeleted(Person person){
        log.info("Controller-PersonController-Post-personDeleted");
        personService.removeById(person.getId());
        return "redirect:/person";
    }

}
