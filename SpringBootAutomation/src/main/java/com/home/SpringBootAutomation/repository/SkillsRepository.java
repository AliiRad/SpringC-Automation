package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.Enum.SkillsGradeEn;
import com.home.SpringBootAutomation.Model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("ALL")
@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long> {

    //TODO: remove method should return the object.
    //TODO: contains the word of the title.
//  @Query("select oo from skillsEntity  where oo.id =: id and update skillsEntity oo set oo.deleted=true where oo.id=:id " )


    //TODO: @Transactional Should be on logicalRemove
    @Transactional
    @Modifying
    @Query("update skillsEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);


    //TODO: how to make it a  dynamic search?
    List<Skills> findSkillsBySkillTitleContaining(String title);
// TODO :   List<Skills> findSkillsBySkillTitleContainingIgnoreCase(String title);

    //TODO : SkillRate is Enum .
    List<Skills> findSkillsByRate(SkillsGradeEn rate);


    List<Skills> findSkillsByTrainingContaining(String training);

//    List<Skills> findSkillsByPersonId(Long id);



}
