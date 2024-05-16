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

    public SkillsServiceImpl(SkillsRepository repository) {
        this.repository = repository;
    }


    @Override
    public Skills save(Skills skills) {
        return repository.save(skills);
    }

    @Override
    public Skills update(Skills skills) throws NoContentException {
        repository.findSkillsByIdAndDeletedFalse(skills.getId()).orElseThrow(
                () ->new NoContentException("No Active Skill Was Found with id "  + skills.getId() +" To Update !")
        );
        return repository.save(skills);
    }

    @Override
    public void logicalRemove(Long id) throws NoContentException {
        repository.findSkillsByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Skill Was Found with id " + id  +" To Remove !")
        );
        repository.logicalRemove(id);
    }

    @Override
    public List<Skills> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Skills> findById(Long id) throws NoContentException {
        Optional<Skills> optional = repository.findById(id);
        if (optional.isPresent()){
            return optional;
        }else {
            throw new NoContentException("No Skill Was Found with id : " + id );
        }
    }

    @Override
    public Long getSkillsCount() {
        return repository.count();
    }

    @Override
    public Skills logicalRemoveWithReturn(Long id) throws NoContentException {
        Skills Skills = repository.findSkillsByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Skill Was Found with id  " + id  +" To Remove !")
        );
        Skills.setDeleted(true);
        return repository.save(Skills);
    }

    @Override
    public List<Skills> findSkillsByDeletedFalse() {
        return repository.findSkillsByDeletedFalse();
    }

    @Override
    public Optional<Skills> findSkillsByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Skills> optional =repository.findSkillsByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active Skill Was Found with id : " + id );
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
