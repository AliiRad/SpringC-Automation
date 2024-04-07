package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.model.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ResponseService {
    Response save(Response response);
    Response edit(Response response);
    Response remove(Response response);
    Response logicalRemove(Long id);
    List<Response> findAll();
    Response findById(Long id);
    List<Response> findByDate(LocalDateTime timeStamp);
    List<Response> findByResponder(String responder);
}
