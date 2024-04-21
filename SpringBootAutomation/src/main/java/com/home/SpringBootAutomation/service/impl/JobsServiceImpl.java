package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.model.Jobs;
import com.home.SpringBootAutomation.repository.JobsRepository;
import com.home.SpringBootAutomation.service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class JobsServiceImpl implements JobsService {

    //------------------------------------------------------
    // Constructor Injection
    private final JobsRepository repository;

    @Autowired
    public JobsServiceImpl (JobsRepository repository) {
        this.repository = repository;
    }

    //------------------------------------------------------

    @Override
    public Jobs save(Jobs jobs) {
        return repository.save(jobs);
    }
    //------------------------------------------------------


    @Override
    public Jobs update(Long id, Jobs jobs) {

        Optional<Jobs> optionalJobs = repository.findJobsByIdAndDeletedFalse(id);

        if (optionalJobs.isPresent()) {
            Jobs oldJobs = optionalJobs.get();
            oldJobs.setCompanyName(jobs.getCompanyName());
            oldJobs.setAddress(jobs.getAddress());
            oldJobs.setPositions(jobs.getPositions());
            oldJobs.setStartDate(jobs.getStartDate());
            oldJobs.setEndDate(jobs.getEndDate());

            return repository.save(oldJobs);
        } else {
            return null;

        }
    }
    //------------------------------------------------------

    @Transactional
    @Override
    public void logicalRemove(Long id) {
        Optional<Jobs> optionalJobs = repository.findJobsByIdAndDeletedFalse(id);
        if (optionalJobs.isPresent()) {
            repository.logicalRemove(id);
        } else {
            System.out.println(" Job not found !");
        }
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

    @Override
    public Long getJobsCount() {
        return repository.count();
    }
    //------------------------------------------------------

    @Transactional
    @Override
    public Jobs logicalRemoveWithReturn(Long id) {
        Optional<Jobs> optionalJobs = repository.findJobsByIdAndDeletedFalse(id);
        if (optionalJobs.isPresent()) {
            Jobs oldJobs = optionalJobs.get();
            oldJobs.setDeleted(true);
            return repository.save(oldJobs);
        } else {
            return null;
        }

    }
    //------------------------------------------------------

    @Override
    public List<Jobs> findJobsByDeletedFalse() {
        return repository.findJobsByDeletedFalse();
    }
    //------------------------------------------------------

    @Override
    public Optional<Jobs> findJobsByIdAndDeletedFalse(Long id) {
        Optional<Jobs> optional = repository.findJobsByIdAndDeletedFalse(id);
        if(optional.isPresent()){
            return optional;
        }else return  Optional.empty();
    }
    //------------------------------------------------------

    @Override
    public List<Jobs> findJobsByCompanyNameAAndDeletedFalse(String companyName) {
        return repository.findJobsByCompanyNameAndDeletedFalse(companyName);
    }
    //------------------------------------------------------

    @Override
    public List<Jobs> findJobsByCompanyNameContainingIgnoreCaseAndDeletedFalse(String companyName) {
        return repository.findJobsByCompanyNameContainingIgnoreCaseAndDeletedFalse(companyName);
    }
    //------------------------------------------------------

    @Override
    public List<Jobs> findJobsByAddressAndDeletedFalse(String address) {
        return repository.findJobsByAddressAndDeletedFalse(address);
    }
    //------------------------------------------------------

    @Override
    public Long countByDeletedFalse() {
        return repository.countByDeletedFalse();
    }
    //------------------------------------------------------

}
