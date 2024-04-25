package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.model.Military;

import java.util.List;

public interface MilitaryService {
    Military save(Military military);
    Military edit(Military military);
    Military remove(Military military);
    Military removeById(Long id);
    List<Military> findAll();
    Military findById (Long id);
}
