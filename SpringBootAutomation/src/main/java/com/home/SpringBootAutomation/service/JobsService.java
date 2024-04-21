package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.model.Jobs;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface JobsService {

    Jobs save(Jobs jobs);
    Jobs update(Long id , Jobs jobs);

    @Transactional
    void logicalRemove(Long id);

    List<Jobs> findAll();
    Jobs findById(Long id);
    Long getJobsCount();


    Jobs logicalRemoveWithReturn(Long id);


    //-------------------------------------------------------------------
    //deletedFalse

    List<Jobs> findJobsByDeletedFalse();
    Optional<Jobs> findJobsByIdAndDeletedFalse(Long id);


    List<Jobs> findJobsByCompanyNameAAndDeletedFalse(String companyName);
    List<Jobs> findJobsByCompanyNameContainingIgnoreCaseAndDeletedFalse(String companyName);
    List<Jobs> findJobsByAddressAndDeletedFalse(String address);

    Long countByDeletedFalse();

}
