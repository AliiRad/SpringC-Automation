package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.Model.Jobs;
import com.home.SpringBootAutomation.repository.JobsRepository;
import com.home.SpringBootAutomation.service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@Service
public class JobsServiceImpl implements JobsService {
    @Autowired
    private JobsRepository repository;

    //------------------------------------------------------
    @Override
    public Jobs save(Jobs jobs) {
        return repository.save(jobs);
    }
    //------------------------------------------------------


    @Override
    public Jobs update(Long id , Jobs jobs) {
        Optional<Jobs> optionalJobs = repository.findById(id);
        if (optionalJobs.isPresent()){
            Jobs oldJobs = optionalJobs.get();
            oldJobs.setAddress(jobs.getAddress());
            oldJobs.setCompanyName(jobs.getCompanyName());
            oldJobs.setPositions(jobs.getPositions());
            oldJobs.setStartDate(jobs.getStartDate());
            oldJobs.setEndDate(jobs.getEndDate());

            return repository.save(oldJobs);
        }
        return null;
    }

    //------------------------------------------------------

    @Transactional
    @Override
    public void logicalRemove(Long id) {
        Optional<Jobs> optionalJobs = repository.findById(id);
        if (optionalJobs.isPresent()){
            repository.logicalRemove(id);
        }
        System.out.println("not found !");

    }

    //------------------------------------------------------

    @Override
    public List<Jobs> findAll() {
        return repository.findAll();
    }

    //------------------------------------------------------

    @Override
    public Jobs findById(Long id) {
        Optional<Jobs> optional = repository.findById(id);
        return optional.orElse(null);
    }

    //------------------------------------------------------

    //TODO: Check if List is Empty or not.
    @Override
    public List<Jobs> findByCompanyName(String companyName) {
        return repository.findByCompanyName(companyName);
    }

    //------------------------------------------------------

    //TODO: Check if List is Empty or not.
    @Override
    public List<Jobs> findByAddress(String address) {
        return repository.findByAddress(address);
    }

    //------------------------------------------------------

    //TODO: Check if object is Empty or not.
//    @Override
//    public Jobs findJobsByPersonId(Long id) {
//
//        return repository.findJobsByPersonId(id);
//    }
    //------------------------------------------------------

    @Override
    public List<Jobs> getJobsByPagination(int pageNo , int pageSize){
        PageRequest pageRequest =  PageRequest.of(pageNo-1 , pageSize);
        Page<Jobs> page = repository.findAll(pageRequest);

        return page.getContent();
    }

    //------------------------------------------------------
    @Override
    public Long getJobsCount(){
        return repository.count();
    }

    //------------------------------------------------------
    @Override
    public Jobs logicalRemoveWithReturn(Long id){
        Optional<Jobs> optionalJobs = repository.findById(id);
        if (optionalJobs.isPresent()){
            Jobs oldJobs = optionalJobs.get();
            oldJobs.setDeleted(true);
            return repository.save(oldJobs);
        }
        return null;
    }
}
