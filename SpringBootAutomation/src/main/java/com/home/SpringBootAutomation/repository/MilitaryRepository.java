package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.enums.MilitaryExemption;
import com.home.SpringBootAutomation.model.Military;
import com.home.SpringBootAutomation.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface MilitaryRepository extends JpaRepository<Military,Long> {
    Optional<Military> findById(Long id);
    List<Military> findAll();
    List<Military> findAllByDeletedFalse();
    List<Military> findAllByDeletedTrue();
    //:todo:Does it true?
    List<Military> findAllByExemption(MilitaryExemption militaryExemption);

    //:todo:Does it true?
    List<Military> findByIssuanceDate(LocalDate issuanceDate);

    Optional<Military> findBySerialNumber(String serialNumber);

    List<Military> findAllByMilitaryVitiationTrue();

    //:todo:Does it true?
    List<Military> findAllByVitiationDate(LocalDate vitiationDate);

    //:todo:Does it true?
    List<Military> findByPerson(Person person);

    @Modifying
    @Query("update militaryEntity  oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Long countByDeletedFalse();

}
