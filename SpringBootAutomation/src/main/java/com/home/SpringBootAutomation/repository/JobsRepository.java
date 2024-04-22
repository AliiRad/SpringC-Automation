package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface JobsRepository extends JpaRepository<Jobs, Long> {

    @Modifying
    @Query("update jobsEntity  oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);


    //fetches all jobs that there deleted field is false.
    List<Jobs> findJobsByDeletedFalse();
    Optional<Jobs> findJobsByIdAndDeletedFalse(Long id);


    List<Jobs> findJobsByCompanyNameAndDeletedFalse(String companyName);
    List<Jobs> findJobsByCompanyNameContainingIgnoreCaseAndDeletedFalse(String companyName);
    List<Jobs> findJobsByAddressAndDeletedFalse(String address);

    Long countByDeletedFalse();


    //TODO: findJobsBySumOfYears() ----> for finding the years of work experience
    // i think this method should be written in personRepository to find people with a given years of work experience.
    //List<Jobs> findJobsByYearOfWorkExperience()
    //Jobs findJobsByPersonId(Long id);





}
