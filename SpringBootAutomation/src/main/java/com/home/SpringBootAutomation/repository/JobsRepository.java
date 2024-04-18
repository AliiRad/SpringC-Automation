package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@SuppressWarnings("ALL")
@Repository
public interface JobsRepository extends JpaRepository<Jobs, Long> {

    @Modifying
    @Transactional
    @Query("update jobsEntity  oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    List<Jobs> findByCompanyName(String companyName);
    List<Jobs> findByAddress(String address);


//    Jobs findJobsByPersonId(Long id);


    //TODO: findJobsBySumOfYears() ----> for finding the years of work experience
    // i think this method should be written in personRepository to find people with a given years of work experience.
//    List<Jobs> findJobsByYearOfWorkExperience()



    //TODO: findAllDeletedFalse()
    //TODO: findAllDeletedTrue()

}
