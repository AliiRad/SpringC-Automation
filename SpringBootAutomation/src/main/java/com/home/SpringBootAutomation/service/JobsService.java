package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Jobs;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public interface JobsService {

    Jobs save(Jobs jobs) throws NoContentException;
    Jobs update(Jobs jobs) throws NoContentException;

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Jobs> findAll();
    Optional<Jobs> findById(Long id)throws NoContentException;
    Long getJobsCount();


    Jobs logicalRemoveWithReturn(Long id) throws NoContentException;


    //-------------------------------------------------------------------
    //deletedFalse

    List<Jobs> findJobsByDeletedFalse();
    Optional<Jobs> findJobsByIdAndDeletedFalse(Long id) throws NoContentException;


    List<Jobs> findJobsByCompanyNameAAndDeletedFalse(String companyName);
    List<Jobs> findJobsByCompanyNameContainingIgnoreCaseAndDeletedFalse(String companyName);
    List<Jobs> findJobsByAddressAndDeletedFalse(String address);

    Long countByDeletedFalse();

}
