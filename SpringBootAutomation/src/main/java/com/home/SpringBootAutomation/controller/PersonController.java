package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.Model.PersonModel;
import com.home.SpringBootAutomation.service.impl.PersonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
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
        model.addAttribute("person",new PersonModel());
        model.addAttribute("personList",personService.findAll());
        return "PersonModel";
    }
    @GetMapping(value = "/id{id}")
    public String showPersonFindById(@ModelAttribute("id")Long id){
        log.info("Controller-PersonController-Get-FindById");
        Optional<PersonModel>person=Optional.ofNullable(personService.findById(id));
        if (person.isPresent()){
            return "PersonModel";
        }else {
            return "error-404";
        }
    }
    @GetMapping(value = "/nationalId")
    public String showPersonFindByNationalId(Model model,@ModelAttribute("nationalId")String nationalId){
        log.info("Controller-PersonController-Get-FindByNationalId");
        List<PersonModel> personModelList =personService.findByNationalId(nationalId);
        if (!personModelList.isEmpty()){
            model.addAttribute("personList", personModelList);
            return "nationalId";
        }else {
            return "error-404";
        }
    }
    @GetMapping(value = "/date")
    public String showPersonFindByBirthDate(Model model, @ModelAttribute("timeStamp") LocalDateTime birthdate){
        log.info("Controller-PersonController-Get-FindByBirthDate");
        List<PersonModel> personModelList =personService.findByBirthDate(birthdate);
        if (!personModelList.isEmpty()){
            model.addAttribute("personList", personModelList);
            return "person";
        }else {
            return "error-404";
        }
    }
    @PostMapping(value = "/save")
    public String savePerson(PersonModel personModel){
        log.info("Controller-PersonController-Post-SavePerson"+ personModel.toString());
        log.info("Controller-PersonController-Post-SavePerson");
        personService.save(personModel);
        return "redirect:/PersonModel";
    }
    @PostMapping(value = "/edit")
    public String editPerson(PersonModel personModel){
        log.info("Controller-PersonController-Post-EditPerson");
        personService.edit(personModel);
        return "person";
    }
    @PostMapping(value = "/delete")
    public String personDeleted(PersonModel personModel){
        log.info("Controller-PersonController-Post-personDeleted");
        personService.removeById(personModel.getId());
        return "redirect:/person";
    }

}
