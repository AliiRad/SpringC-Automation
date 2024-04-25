package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.Military;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MilitaryRepository extends JpaRepository<Military,Long> {

}
