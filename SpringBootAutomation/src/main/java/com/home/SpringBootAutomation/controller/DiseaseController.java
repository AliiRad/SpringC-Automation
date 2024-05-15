package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Disease;
import com.home.SpringBootAutomation.model.Ticket;
import com.home.SpringBootAutomation.service.impl.DiseaseServiceImp;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping(value = "/disease")
public class DiseaseController {
    private DiseaseServiceImp diseaseServiceImp;

    public DiseaseController (DiseaseServiceImp diseaseServiceImp) {
        this.diseaseServiceImp = diseaseServiceImp;
    }

    @GetMapping
    public String findAllDisease(Model model) {
            log.info("Disease_Controller-Get-FindALL");
            model.addAttribute("disease", new Disease());
            model.addAttribute("diseaseList", diseaseServiceImp.findAll());
            return "disease";

        }

    @PostMapping()
    @ResponseBody
    public Disease saveDisease(@Valid Disease disease, BindingResult bindingResult) throws NoContentException {
        if (bindingResult.hasErrors()) {
            log.error("Disease_Controller-Post-Save-Error: " + disease.toString());
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        log.info("Disease_Controller-Post-Save: " + disease.toString());
        return diseaseServiceImp.save(disease);
    }


    @PutMapping()
    @ResponseBody
    public Disease editDisease(Disease disease, BindingResult bindingResult) throws NoContentException {
        if (bindingResult.hasErrors()) {
            log.error("Disease_Controller-Put-Edit: " + disease.toString());
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        return diseaseServiceImp.edit(disease);

    }



    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public Disease deleteDisease(@PathVariable Long id) throws NoContentException {
        log.info("Disease_Controller-Delete-Delete: " + id);
        return diseaseServiceImp.logicalRemove(id);
    }




    @GetMapping(value = "/{id}")
    @ResponseBody
    public Disease singleDisease(@PathVariable("id") Long id) throws NoContentException {
        log.info("Disease_Controller-Get-FindById: " + id);
        return diseaseServiceImp.findById(id);
    }

    @GetMapping(value = "/name/{name}")
    @ResponseBody
    public List<Disease> DiseaseByName(@PathVariable("name") String name) throws NoContentException {
        log.info("Disease_Controller-Get-FindByName");
        List<Disease> diseaseList = diseaseServiceImp.findDiseaseByName(name);
        return ((diseaseList.isEmpty()) ? null : diseaseList);
    }
    @GetMapping(value = "/type/{type}")
    @ResponseBody
    public List<Disease> DiseaseByType(@PathVariable("type") String type) throws NoContentException {
        log.info("Disease_Controller-Get-FindByType");
        List<Disease> diseaseList = diseaseServiceImp.findDiseaseByType(type);
        return ((diseaseList.isEmpty()) ? null : diseaseList);
    }
    }


