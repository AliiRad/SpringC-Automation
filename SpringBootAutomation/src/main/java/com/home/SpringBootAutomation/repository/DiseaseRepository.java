package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.Disease;
import com.home.SpringBootAutomation.model.MedicalHistory;
import com.home.SpringBootAutomation.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.lang.model.element.Name;
import java.lang.reflect.Type;
import java.util.List;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease,Long> {
    @Query("select oo from diseaseEntity oo where  oo.deleted=false ")
    List<Disease> findAllDeletedFalse();

    @Query("select oo from diseaseEntity oo where oo.name=: name")
    List<Disease>findDiseaseByName(String name);

    @Query("select oo from diseaseEntity oo where oo.type=: type")
    List<Disease>findDiseaseByType(String type);
}
