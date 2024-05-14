package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Response;

import java.time.LocalDateTime;
import java.util.List;

public interface ResponseService {
    Response save(Response response);
    Response edit(Response response) throws NoContentException;
    void remove(Response response) throws NoContentException;
    Response logicalRemove(Long id) throws NoContentException;
    List<Response> findAll();
    Response findById(Long id) throws NoContentException;
    List<Response> findByDate(LocalDateTime responseTimeStamp);
//    List<Response> findByResponder(String responder);
    List<Response> findByTicketGroup(String title);
}
