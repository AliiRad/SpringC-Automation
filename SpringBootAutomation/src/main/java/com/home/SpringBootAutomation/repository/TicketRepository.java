package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.PersonModel;
import com.home.SpringBootAutomation.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

//    @Query("select oo from ticketEntity oo where oo.applicant.username=:applicant and oo.active=true")
    List<Ticket> findByApplicant(PersonModel applicant);

    @Query("select oo from ticketEntity oo where oo.ticketTimeStamp=:ticketDate and oo.active=true")
    List<Ticket> findByDate(LocalDateTime ticketDate);

//    @Transactional
//    @Modifying
//    @Query("update  ticketEntity oo set oo.active=false where oo.id=:id")
//    void logicalDelete(Long id);
}
