package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.Model.PersonModel;

import java.util.List;

public interface PersonService {
    PersonModel save(PersonModel personModel);

    PersonModel edit(PersonModel personModel);

    PersonModel remove(PersonModel personModel);

    PersonModel removeById(Long id);

    List<PersonModel> findAll();

    PersonModel findById(Long id);

    List<PersonModel> findByNationalId(String nationalID);

}
