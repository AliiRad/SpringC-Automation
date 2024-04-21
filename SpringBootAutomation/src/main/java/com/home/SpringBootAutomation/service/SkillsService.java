package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.Enum.SkillsGradeEn;
import com.home.SpringBootAutomation.model.Skills;

import java.util.List;

public interface SkillsService {
    Skills save (Skills skills);
    Skills update(Long id , Skills skills);
    void logicalRemove(Long id);
    List<Skills> findAll();
    Skills findById(Long id);
    List<Skills> findSkillsBySkillTitleContaining(String title);
    List<Skills> findSkillsByRate(SkillsGradeEn rate);
    List<Skills> findSkillsByTrainingContaining(String training);
    //    List<Skills> findSkillsByPersonId(Long id);
    List<Skills> getSkillsByPagination(int pageNo, int pageSize);
    Long getSkillsCount();
    Skills logicalRemoveWithReturn(Long id);

}
