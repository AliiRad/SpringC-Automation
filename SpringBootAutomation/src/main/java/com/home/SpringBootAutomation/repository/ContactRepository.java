package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

//        @Modifying
//        @Query("update contactEntity oo set oo.deleted=true where oo.id=:id")
//        void logicalRemove(Long id);
//
//        List<Contact> findContactByDeletedFalse();
//
//        Optional<Contact> findContactByIdAndDeletedFalse(Long id);
//
//        Long countByDeletedFalse();
}
