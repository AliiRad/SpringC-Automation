package com.home.SpringBootAutomation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.home.SpringBootAutomation.model.Section;

public interface SectionRepository extends JpaRepository<Section,Long>{

    @Modifying
    @Query("update sectionEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<Section> findSectionByIdAndDeletedFalse(Long id);

    Optional<Section> findPersonByIdAndDeletedFalse(Long id);

}
