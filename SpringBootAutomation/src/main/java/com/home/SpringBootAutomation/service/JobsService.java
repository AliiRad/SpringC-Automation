package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.Model.Jobs;

import java.util.List;

public interface JobsService {
    Jobs save(Jobs jobs) ;
    Jobs update(Long id , Jobs jobs) ;
    void logicalRemove(Long id);
    List<Jobs> findAll();
    Jobs findById(Long id);
    List<Jobs> findByCompanyName(String companyName);
    List<Jobs> findByAddress(String address);
    //    Jobs findJobsByPersonId(Long id);
    List<Jobs> getJobsByPagination(int pageNo, int pageSize);
    Long getJobsCount();

}
