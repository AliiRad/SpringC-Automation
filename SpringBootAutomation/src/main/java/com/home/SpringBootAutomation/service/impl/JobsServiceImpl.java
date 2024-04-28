package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Jobs;
import com.home.SpringBootAutomation.repository.JobsRepository;
import com.home.SpringBootAutomation.service.JobsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class JobsServiceImpl implements JobsService {

    private final JobsRepository repository;

    public JobsServiceImpl (JobsRepository repository) {
        this.repository = repository;
    }


    @Override
    public Jobs save(Jobs jobs) {
        return repository.save(jobs);
    }


    @Override
    public Jobs update(Long id, Jobs jobs) throws NoContentException {

        Optional<Jobs> optionalJobs = repository.findJobsByIdAndDeletedFalse(id);

        if (optionalJobs.isPresent()) {
        //TODO: using this way to prevent edition of id and deleted .

//            Jobs oldJobs = optionalJobs.get();
//            oldJobs.setCompanyName(jobs.getCompanyName());
//            oldJobs.setAddress(jobs.getAddress());
//            oldJobs.setPositions(jobs.getPositions());
//            oldJobs.setStartDate(jobs.getStartDate());
//            oldJobs.setEndDate(jobs.getEndDate());

            return repository.save(jobs);
        } else {
            throw new NoContentException("Job not found !");

        }
    }

    @Transactional
    @Override
    public void logicalRemove(Long id) throws NoContentException {
        Optional<Jobs> optionalJobs = repository.findJobsByIdAndDeletedFalse(id);
        if (optionalJobs.isPresent()) {
            repository.logicalRemove(id);
        } else {
            throw new NoContentException("Job not found !");
        }
    }

    @Override
    public List<Jobs> findAll() {
        //TODO:Check list length
        return repository.findAll();
    }

    @Override
    public Optional<Jobs> findById(Long id) throws NoContentException {
        Optional<Jobs> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("Job not found !");
        }
    }

    @Override
    public Long getJobsCount() {
        return repository.count();
    }

    @Override
    public Jobs logicalRemoveWithReturn(Long id) throws NoContentException {
        Optional<Jobs> optionalJobs = repository.findJobsByIdAndDeletedFalse(id);
        if (optionalJobs.isPresent()) {
            Jobs oldJobs = optionalJobs.get();
            oldJobs.setDeleted(true);
            return repository.save(oldJobs);
        } else {
            throw new NoContentException("Job not found !");

        }

    }

    @Override
    public List<Jobs> findJobsByDeletedFalse() {
        return repository.findJobsByDeletedFalse();
    }

    @Override
    public Optional<Jobs> findJobsByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Jobs> optional = repository.findJobsByIdAndDeletedFalse(id);
        if(optional.isPresent()){
            return optional;
        }else {
            throw new NoContentException("Job not found !");

        }
    }

    @Override
    public List<Jobs> findJobsByCompanyNameAAndDeletedFalse(String companyName) {
        return repository.findJobsByCompanyNameAndDeletedFalse(companyName);
    }

    @Override
    public List<Jobs> findJobsByCompanyNameContainingIgnoreCaseAndDeletedFalse(String companyName) {
        return repository.findJobsByCompanyNameContainingIgnoreCaseAndDeletedFalse(companyName);
    }

    @Override
    public List<Jobs> findJobsByAddressAndDeletedFalse(String address) {
        return repository.findJobsByAddressAndDeletedFalse(address);
    }

    @Override
    public Long countByDeletedFalse() {
        return repository.countByDeletedFalse();
    }

}
