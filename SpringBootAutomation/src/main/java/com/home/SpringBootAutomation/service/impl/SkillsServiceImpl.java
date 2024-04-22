package com.home.SpringBootAutomation.service.impl;



import com.home.SpringBootAutomation.enums.SkillsGradeEn;
import com.home.SpringBootAutomation.model.Skills;
import com.home.SpringBootAutomation.repository.SkillsRepository;
import com.home.SpringBootAutomation.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class SkillsServiceImpl implements SkillsService {


    //------------------------------------------------------
    // Constructor Injection
    private final SkillsRepository repository;

    @Autowired
    public SkillsServiceImpl (SkillsRepository repository) {
        this.repository = repository;
    }

    //------------------------------------------------------


    @Override
    public Skills save(Skills skills) {
        return repository.save(skills);
    }
    //------------------------------------------------------


    @Override
    public Skills update(Long id, Skills skills) {
        Optional<Skills> optionalSkills = repository.findSkillsByIdAndDeletedFalse(id);

        if (optionalSkills.isPresent()){
            Skills oldSkills = optionalSkills.get();
            oldSkills.setSkillTitle(skills.getSkillTitle());
            oldSkills.setRate(skills.getRate());
            oldSkills.setTraining(skills.getTraining());
            oldSkills.setDescription(skills.getDescription());
            oldSkills.setCertification(skills.getCertification());



            return repository.save(oldSkills);
        }else {
            return null;
        }
    }
    //------------------------------------------------------

    @Transactional
    @Override
    public void logicalRemove(Long id) {
        Optional<Skills> optionalSkills = repository.findSkillsByIdAndDeletedFalse(id);
        if (optionalSkills.isPresent()){
            repository.logicalRemove(id);
        }else {
            System.out.println(" Skill not found !");
        }


    }
    //------------------------------------------------------


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


    @Override
    public Long getSkillsCount() {
        return repository.count();
    }
    //------------------------------------------------------


    @Override
    public Skills logicalRemoveWithReturn(Long id) {
        Optional<Skills> optionalSkills = repository.findSkillsByIdAndDeletedFalse(id);
        if (optionalSkills.isPresent()){
            Skills oldSkills = optionalSkills.get();
            oldSkills.setDeleted(true);
            return repository.save(oldSkills);
        } else {
            return null;
        }
    }
    //------------------------------------------------------


    @Override
    public List<Skills> findSkillsByDeletedFalse() {
        return repository.findSkillsByDeletedFalse();
    }
    //------------------------------------------------------


    @Override
    public Optional<Skills> findSkillsByIdAndDeletedFalse(Long id) {
        Optional<Skills> optional = repository.findSkillsByIdAndDeletedFalse(id);
        if (optional.isPresent()){
            return optional;
        }else return  Optional.empty();
    }
    //------------------------------------------------------


    @Override
    public List<Skills> findSkillsBySkillTitleContainingIgnoreCaseAndDeletedFalse(String title) {
        return repository.findSkillsBySkillTitleContainingIgnoreCaseAndDeletedFalse(title);
    }
    //------------------------------------------------------


    @Override
    public List<Skills> findSkillsByRateAndDeletedFalse(SkillsGradeEn rate) {
        return repository.findSkillsByRateAndDeletedFalse(rate);
    }
    //------------------------------------------------------


    @Override
    public List<Skills> findSkillsByTrainingContainingIgnoreCaseAndDeletedFalse(String training) {
        return repository.findSkillsByTrainingContainingIgnoreCaseAndDeletedFalse(training);
    }
    //------------------------------------------------------

    @Override
    public Long countByDeletedFalse() {
        return repository.countByDeletedFalse();
    }
    //------------------------------------------------------

}
