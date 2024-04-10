package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.Model.Bank;
import com.home.SpringBootAutomation.repository.BankRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImp implements BankService{
    private final BankRepository repository;

    public BankServiceImp(BankRepository repository) {
        this.repository = repository;
    }

    @Override
    public Bank save(Bank bank) {
        repository.save(bank);
        return bank;
    }

    @Override
    public Bank edit(Bank bank) {
        repository.save(bank);
        return bank;
    }

    @Override
    public Bank remove(Bank bank) {
        if (findById(bank.getId()) != null) {
            repository.delete(bank);
            return bank;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Bank logicalRemove(Long id) {
        Bank bank = findById(id);
        if (bank != null) {
            repository.save(bank);
            return bank;
        } else{
            return null;
        }
    }

    @Override
    public Bank findById(Long id) {
       Optional<Bank> bank= repository.findById(id);
       return (bank.orElse(null));
    }

    @Override
    public List<Bank> findAll() {
        return repository.findAll();
    }
}
