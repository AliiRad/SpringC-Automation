package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Military;
import com.home.SpringBootAutomation.model.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MilitaryService {

    Military save(Military military);

    Military update(Military military) throws NoContentException;

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Military> findAll();

    Optional<Military> findById(Long id) throws NoContentException;

    Long getMilitaryCount();

    @Transactional
    void militaryVitiation(Long id) throws NoContentException;

    //deletedFalse

    List<Military> findMilitaryByDeletedFalse();

    Optional<Military> findMilitaryByIdAndDeletedFalse(Long id) throws NoContentException;

    Military findMilitaryByNameAndLastnameAndDeletedFalse(String name,String lastname);//todo:does it true?

    Optional<Military> findMilitaryBySerialNumberAndDeletedFalse(String serialNumber) throws NoContentException;

    Long countByDeletedFalse();
}
