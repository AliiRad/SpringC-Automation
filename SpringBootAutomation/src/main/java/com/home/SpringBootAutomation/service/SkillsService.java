package com.home.SpringBootAutomation.service;


import com.home.SpringBootAutomation.enums.SkillsGradeEn;
import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Skills;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public interface SkillsService {

    Skills save(Skills skills);
    Skills update(Skills skills) throws NoContentException;

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Skills> findAll();
   Optional<Skills>findById(Long id)throws NoContentException;
    Long getSkillsCount();

    Skills logicalRemoveWithReturn(Long id) throws NoContentException;

    //-------------------------------------------------------------------
    //deletedFalse

    List<Skills> findSkillsByDeletedFalse();
    Optional<Skills> findSkillsByIdAndDeletedFalse(Long id) throws NoContentException;


    List<Skills> findSkillsBySkillTitleContainingIgnoreCaseAndDeletedFalse(String title);
    List<Skills> findSkillsByRateAndDeletedFalse(SkillsGradeEn rate);
    List<Skills> findSkillsByTrainingContainingIgnoreCaseAndDeletedFalse(String training);

    Long countByDeletedFalse();
}
