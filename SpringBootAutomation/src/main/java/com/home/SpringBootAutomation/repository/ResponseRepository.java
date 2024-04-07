package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ResponseRepository extends JpaRepository<Response , Long> {
    @Query("select oo from responseEntity oo where oo.timeStamp=:timeStamp")
    List<Response> findByDate(LocalDateTime timeStamp);

    @Query("select oo from responseEntity oo where oo.responder=:responder")
    List<Response> findByResponder(String responder);

    @Query("update  responseEntity set active=false where id=:id")
    Response logicalDelete(Long id);
}
