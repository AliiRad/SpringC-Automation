package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.enums.SkillsGradeEn;
import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Skills;
import com.home.SpringBootAutomation.repository.SkillsRepository;
import com.home.SpringBootAutomation.service.SkillsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SkillsServiceImpl implements SkillsService {


    private final SkillsRepository repository;

    public SkillsServiceImpl (SkillsRepository repository) {
        this.repository = repository;
    }


    @Override
    public Skills save(Skills skills) {
        return repository.save(skills);
    }


    @Override
    public Skills update(Skills skills) throws NoContentException {
        Optional<Skills> optionalSkills = repository.findSkillsByIdAndDeletedFalse(skills.getId());

        if (optionalSkills.isPresent()){
//            Skills oldSkills = optionalSkills.get();
//            oldSkills.setSkillTitle(skills.getSkillTitle());
//            oldSkills.setRate(skills.getRate());
//            oldSkills.setTraining(skills.getTraining());
//            oldSkills.setDescription(skills.getDescription());
//            oldSkills.setCertification(skills.getCertification());


            return repository.save(skills);
        } else {
            throw new NoContentException("Skill not found !");
        }
    }

    @Transactional
    @Override
    public void logicalRemove(Long id) throws NoContentException {
        Optional<Skills> optionalSkills = repository.findSkillsByIdAndDeletedFalse(id);
        if (optionalSkills.isPresent()){
            repository.logicalRemove(id);
        }else {
            throw new NoContentException("Skill not found !");
        }
    }


    @Override
    public List<Skills> findAll() {
        return repository.findAll();
    }


    @Override
    public Optional<Skills> findById(Long id) throws NoContentException {
        Optional<Skills> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("Skill not found !");
        }
    }

    @Override
    public Long getSkillsCount() {
        return repository.count();
    }


    @Override
    public Skills logicalRemoveWithReturn(Long id) throws NoContentException {
        Optional<Skills> optionalSkills = repository.findSkillsByIdAndDeletedFalse(id);

        if (optionalSkills.isPresent()){
            Skills oldSkills = optionalSkills.get();
            oldSkills.setDeleted(true);
            return repository.save(oldSkills);

        } else {
            throw new NoContentException("Skill not found !");

        }
    }

    @Override
    public List<Skills> findSkillsByDeletedFalse() {
        return repository.findSkillsByDeletedFalse();
    }


    @Override
    public Optional<Skills> findSkillsByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Skills> optional = repository.findSkillsByIdAndDeletedFalse(id);
        if (optional.isPresent()){
            return optional;
        }else {
            throw new NoContentException("Skill not found !");

        }
    }


    @Override
    public List<Skills> findSkillsBySkillTitleContainingIgnoreCaseAndDeletedFalse(String title) {
        return repository.findSkillsBySkillTitleContainingIgnoreCaseAndDeletedFalse(title);
    }


    @Override
    public List<Skills> findSkillsByRateAndDeletedFalse(SkillsGradeEn rate) {
        return repository.findSkillsByRateAndDeletedFalse(rate);
    }


    @Override
    public List<Skills> findSkillsByTrainingContainingIgnoreCaseAndDeletedFalse(String training) {
        return repository.findSkillsByTrainingContainingIgnoreCaseAndDeletedFalse(training);
    }

    @Override
    public Long countByDeletedFalse() {
        return repository.countByDeletedFalse();
    }

}
