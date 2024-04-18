package com.home.SpringBootAutomation.service.impl;


import com.home.SpringBootAutomation.Enum.SkillsGradeEn;
import com.home.SpringBootAutomation.model.Skills;
import com.home.SpringBootAutomation.repository.SkillsRepository;
import com.home.SpringBootAutomation.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@SuppressWarnings("ALL")
@Service
public class SkillsServiceImpl implements SkillsService {


    //------------------------------------------------------

    private SkillsRepository repository;

    @Autowired
    public void setRepository(SkillsRepository repository){
        this.repository = repository;
    }

    //------------------------------------------------------

//    @Autowired
//    private SkillsRepository repository;
    //------------------------------------------------------


    @Override
    public Skills save(Skills skills) {
        return repository.save(skills);
    }
    //------------------------------------------------------


    @Override
    public Skills update(Long id, Skills skills) {
        Optional<Skills> optionalSkills = repository.findById(id);
        if (optionalSkills.isPresent()){
            Skills oldSkills = optionalSkills.get();
            oldSkills.setSkillTitle(skills.getSkillTitle());
            oldSkills.setRate(skills.getRate());
            oldSkills.setCertification(skills.getCertification());
            oldSkills.setDescription(skills.getDescription());
            oldSkills.setTraining(skills.getTraining());

            return repository.save(oldSkills);
        }else {
            return null;
        }

    }
    //------------------------------------------------------


    @Transactional
    @Override
    public void logicalRemove(Long id) {
        Optional<Skills> optional = repository.findById(id);
        if (optional.isPresent()){
            repository.logicalRemove(id);
        }else {
            System.out.println(" Skill not found !");
        }


    }

    //------------------------------------------------------

    //TODO : Check if list is Empty

    @Override
    public List<Skills> findAll() {
        return repository.findAll();
    }

    //------------------------------------------------------

    @Override
    public Skills findById(Long id) {
        Optional<Skills> optional = repository.findById(id);
        return optional.orElse(null);
    }

    //------------------------------------------------------


    //TODO: Check if List is Empty or not.
    @Override
    public List<Skills> findSkillsBySkillTitleContaining(String title) {
        return repository.findSkillsBySkillTitleContaining(title);
    }
    //------------------------------------------------------


    //TODO: Check if List is Empty or not.
    @Override
    public List<Skills> findSkillsByRate(SkillsGradeEn rate) {
        return repository.findSkillsByRate(rate);
    }
    //------------------------------------------------------


    //TODO: Check if List is Empty or not.
    @Override
    public List<Skills> findSkillsByTrainingContaining(String training) {
        return repository.findSkillsByTrainingContaining(training);
    }
    //------------------------------------------------------


    //TODO: Check if List is Empty or not.
//    @Override
//    public List<Skills> findSkillsByPersonId(Long id) {
//        return repository.findSkillsByPersonId(id);
//    }
    //------------------------------------------------------


    //TODO: Check if List is Empty or not.
    @Override
    public List<Skills> getSkillsByPagination(int pageNo , int pageSize){
        PageRequest pageRequest =PageRequest.of(pageNo-1 , pageSize);
        Page<Skills> page = repository.findAll(pageRequest);

        return page.getContent();
    }
    //------------------------------------------------------

    @Override
    public Long getSkillsCount(){
        return repository.count();
    }

    //------------------------------------------------------
    @Override
    public Skills logicalRemoveWithReturn(Long id){
        Optional<Skills> optionalSkills = repository.findById(id);
        if (optionalSkills.isPresent()){
            Skills oldSkills = optionalSkills.get();
            oldSkills.setDeleted(true);
            return repository.save(oldSkills);
        }else {
            return null;
        }

    }

    //------------------------------------------------------


}
