package com.home.SpringBootAutomation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.Organisation;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Long>{

    @Modifying
    @Query("update organisationEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<Organisation> findOrganisationByIdAndDeletedFalse(Long id) throws NoContentException;

    List<Organisation> findOrganisationByDeletedFalse() throws NoContentException;

}
