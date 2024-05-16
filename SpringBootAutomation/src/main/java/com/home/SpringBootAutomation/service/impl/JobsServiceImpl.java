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

    public JobsServiceImpl(JobsRepository repository) {
        this.repository = repository;
    }


    @Override
    public Jobs save(Jobs jobs)  {
        return repository.save(jobs);
    }

    @Override
    public Jobs update(Jobs jobs) throws NoContentException {
        repository.findJobsByIdAndDeletedFalse(jobs.getId()).orElseThrow(
                () -> new NoContentException("No Active Job Was Found with id " + jobs.getId() + " To Update !" )
        );
        return repository.save(jobs);
    }

    @Override
    public void logicalRemove(Long id) throws NoContentException {
        repository.findJobsByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Job Was Found with id " + id + " To Remove !" )
        );
        repository.logicalRemove(id);
    }

    @Override
    public List<Jobs> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Jobs> findById(Long id) throws NoContentException {
        Optional<Jobs> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional;
        }else {
            throw new NoContentException("No Job Was Found with id : " + id );
        }

    }

    @Override
    public Long getJobsCount() {
        return repository.count();
    }

    @Override
    public Jobs logicalRemoveWithReturn(Long id) throws NoContentException {
        Jobs jobs = repository.findJobsByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Job Was Found with id  " + id  +" To Remove !")
        );
        jobs.setDeleted(true);
        return repository.save(jobs);
    }

    @Override
    public List<Jobs> findJobsByDeletedFalse() {
        return repository.findJobsByDeletedFalse();
    }

    @Override
    public Optional<Jobs> findJobsByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Jobs> optional = repository.findJobsByIdAndDeletedFalse(id);
        if (optional.isPresent()){
            return optional;
        }else {
            throw new NoContentException("No Active Job Was Found with id : " + id );
        }
    }

    @Override
    public List<Jobs> findJobsByCompanyNameAndDeletedFalse(String companyName) {
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
