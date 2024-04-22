package com.home.SpringBootAutomation.repository;


import com.home.SpringBootAutomation.enums.SkillsGradeEn;
import com.home.SpringBootAutomation.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long> {


    @Modifying
    @Query("update jobsEntity  oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    List<Skills> findSkillsByDeletedFalse();
    Optional<Skills> findSkillsByIdAndDeletedFalse(Long id);


    List<Skills> findSkillsBySkillTitleContainingIgnoreCaseAndDeletedFalse(String title);
    List<Skills> findSkillsByRateAndDeletedFalse(SkillsGradeEn rate);
    List<Skills> findSkillsByTrainingContainingIgnoreCaseAndDeletedFalse(String training);

    Long countByDeletedFalse();




}
