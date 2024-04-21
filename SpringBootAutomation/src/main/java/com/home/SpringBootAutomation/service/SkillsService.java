package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.Enum.SkillsGradeEn;
import com.home.SpringBootAutomation.model.Skills;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SkillsService {

    Skills save(Skills skills);
    Skills update(Long id , Skills skills);

    @Transactional
    void logicalRemove(Long id);

    List<Skills> findAll();
    Skills findById(Long id);
    Long getSkillsCount();

    Skills logicalRemoveWithReturn(Long id);

    //-------------------------------------------------------------------
    //deletedFalse

    List<Skills> findSkillsByDeletedFalse();
    Optional<Skills> findSkillsByIdAndDeletedFalse(Long id);


    List<Skills> findSkillsBySkillTitleContainingIgnoreCaseAndDeletedFalse(String title);
    List<Skills> findSkillsByRateAndDeletedFalse(SkillsGradeEn rate);
    List<Skills> findSkillsByTrainingContainingIgnoreCaseAndDeletedFalse(String training);

    Long countByDeletedFalse();
}
