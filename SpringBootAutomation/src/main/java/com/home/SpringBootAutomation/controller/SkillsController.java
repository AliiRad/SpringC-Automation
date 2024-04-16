package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.Enum.SkillsGradeEn;
import com.home.SpringBootAutomation.Model.Skills;
import com.home.SpringBootAutomation.service.SkillsService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("ALL")
@Controller
@Slf4j
@RequestMapping("/skills")
public class SkillsController {



//    private SkillsServiceI service;
//
//    @Autowired
//    public void setService(SkillsService service) {
//        this.service = service;
//    }
    //    -------------------------------------------------------------------------


    @Autowired
    private SkillsService service;

    //    -------------------------------------------------------------------------


    @PostMapping("/save")
    public String save(@Valid Skills skills, Model model, BindingResult result) {
        if (result.hasErrors()) {
            log.error(result.getAllErrors().toString());
//                model.addAttribute("error" ,result.getAllErrors().toString());
            return "skills";
        }else {
            log.info("Skill Saved - Post Method");
            log.info(skills.toString());
            service.save(skills);

            return "redirect:/skills";
        }

    }
    //    -------------------------------------------------------------------------

    @PostMapping("/edit")
    public String edit(Long id, @Valid Skills skills, Model model) {
        log.info("Skill Edited - Post Method");


        if (service.findById(id) != null) {
            service.update(id, skills);

            model.addAttribute("skills", skills);
            model.addAttribute("message", "Skill Edited Successfully !");
            return "redirect:/skills";
        }else {
            model.addAttribute("message", "Skill Not Found !");
            return "redirect:/skills";
        }

    }


    //    -------------------------------------------------------------------------


    @PostMapping("/delete")
    public String delete(Long id, Model model) {
        log.info("Skill Deleted - Post Method");
        service.logicalRemove(id);
        model.addAttribute("message", "Skill Deleted !");
        return "redirect:/skills";

    }


    //    -------------------------------------------------------------------------

    @GetMapping
    public String findAll(Model model) {


        log.info("Skill Founded - Get Method");
        model.addAttribute("skills", new Skills());
        model.addAttribute("skillsList", service.findAll());
        return "skills";
    }

    //    -------------------------------------------------------------------------


    @GetMapping("/findById/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        Skills skills = service.findById(id);
        model.addAttribute("skills", skills);
        return "skillsFilter";
    }

    //    -------------------------------------------------------------------------

    @GetMapping("/titleContains/{title}")
    public String findSkillsBySkillTitleContaining(@PathVariable("title") String title , Model model){

        log.info("Find By Pattern - Get Method");


        model.addAttribute("skills" , new Skills());
        model.addAttribute("skillsList" ,service.findSkillsBySkillTitleContaining(title) );

        return "skillsFilter";

    }

    //    -------------------------------------------------------------------------

    @GetMapping("/findSkillsByRate/{rate}")
    public String findSkillsByRate(@PathVariable("rate") SkillsGradeEn rate , Model model){

        log.info("Find By Rate - Get Method");

        model.addAttribute("skills" , new Skills());
        model.addAttribute("skillsList" , service.findSkillsByRate(rate));

        return "skillsFilter";
    }

    //    -------------------------------------------------------------------------

    @GetMapping("/findSkillsByTraining/{training}")
    public String findSkillsByTrainingContaining(@PathVariable("training") String training , Model model){

        log.info("Find By Training - Get Method");

        model.addAttribute("skills" , new Skills());
        model.addAttribute("skillsList" , service.findSkillsByTrainingContaining(training));

        return "skillsFilter";
    }
    //    -------------------------------------------------------------------------


    @GetMapping("/findALlByPagination")
    public String getSkillsByPagination(@RequestParam(defaultValue = "1") Integer pageNo,
                                        @RequestParam(defaultValue = "5") Integer pageSize,
                                        Model model){

        log.info("Find By Pagination - Get Method");


        model.addAttribute("skills" , service.getSkillsByPagination(pageNo , pageSize));
        model.addAttribute("totalPages" , ((int)(service.getSkillsCount()/pageSize)) + 1);
        model.addAttribute("currentPage" , pageNo);
        return "skills";
    }


    //    -------------------------------------------------------------------------










    //    -------------------------------------------------------------------------


//For Returning Json
//    @GetMapping("/findById/{id}")
//    @ResponseBody
//    public ResponseEntity<Skills> findById(@PathVariable("id") Long id) {
//        Skills skills = service.findById(id);
//        if (skills != null) {
//            return ResponseEntity.ok(skills);
//        } else {
//            return ResponseEntity.notFound().build();
//
//        }
//
//    }
// }


//    @GetMapping("/findById/{id}")
//    @ResponseBody
//    public Skills findById(@PathVariable("id") Long id) {
//        Skills skills = service.findById(id);
//        if (skills != null) {
//            return service.findById(id);
//        }
//       return null;
//
//    }

}
