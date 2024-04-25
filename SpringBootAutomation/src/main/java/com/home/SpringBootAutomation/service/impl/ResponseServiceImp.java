package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.model.Response;
import com.home.SpringBootAutomation.repository.ResponseRepository;
import com.home.SpringBootAutomation.service.ResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ResponseServiceImp implements ResponseService {
    private ResponseRepository responseRepository;

    public ResponseServiceImp(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    @Override
    public Response save(Response response) {
        log.info("Service-Response-Save");
        response.setDeleted(true);
        response.setResponseTimeStamp(LocalDateTime.now());
        responseRepository.save(response);
        return response;
    }

    @Override
    public Response edit(Response response) {
        log.info("Service-Response-Edit");
        response.setDeleted(true);
        if (findById(response.getId()) != null) {
            responseRepository.save(response);
            return response;
        } else return null;
    }

    @Override
    public Response remove(Response response) {
        log.info("Service-Response-Remove");
        if (findById(response.getId()) != null) {
            responseRepository.delete(response);
            return response;
        } else return null;
    }

    @Override
    public Response logicalRemove(Long id) {
        log.info("Service-Response-LogicalRemove");
        Response response = findById(id);
        if (response != null) {
            response.setDeleted(false);
            responseRepository.save(response);
            return response;
        } else return null;
    }

    @Override
    public List<Response> findAll() {
        log.info("Service-Response-FindAll");
        List<Response> responseList = responseRepository.findAll();
        return responseList;
    }

    @Override
    public Response findById(Long id) {
        log.info("Service-Response-FindById");
        Optional<Response> response = responseRepository.findById(id);
        return (response.isPresent() ? response.get() : null);
    }

    @Override
    public List<Response> findByDate(LocalDateTime responseDate) {
        log.info("Service-Response-FindByDate");
        List<Response> responseList = responseRepository.findByDate(responseDate);
        return responseList;
    }

    @Override
    public List<Response> findByResponder(String responder) {
        log.info("Service-Response-FindByResponder");
        List<Response> responseList = responseRepository.findByResponder(responder);
        return responseList;
    }
}
