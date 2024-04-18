package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.model.MilitaryServiceModel;

import java.util.List;

public interface MilitaryService {
    MilitaryServiceModel save(MilitaryServiceModel militaryServiceModel);
    MilitaryServiceModel edit(MilitaryServiceModel militaryServiceModel);
    MilitaryServiceModel remove(MilitaryServiceModel militaryServiceModel);
    MilitaryServiceModel removeById(Long id);
    List<MilitaryServiceModel> findAll();
    MilitaryServiceModel findById (Long id);
}
