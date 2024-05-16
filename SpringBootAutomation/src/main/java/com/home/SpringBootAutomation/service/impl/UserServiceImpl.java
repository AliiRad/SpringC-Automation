package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.exceptions.NoUserException;
import com.home.SpringBootAutomation.model.User;
import com.home.SpringBootAutomation.repository.UserRepository;
import com.home.SpringBootAutomation.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }
    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User update(User user) throws NoContentException {
        repository.findUserByIdAndDeletedFalse(user.getId()).orElseThrow(
                () -> new NoContentException("No Active User Was Found With ID " +user.getId()+  " To Update !")
        );
        return repository.save(user);
    }

    @Override
    public void logicalRemove(Long id) throws NoContentException {
        repository.findUserByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active User Was Found With ID " +id+  " To Remove !")
        );
        repository.logicalRemove(id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) throws NoContentException {
        Optional<User> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No User Was Found with id : " + id );
        }
    }

    @Override
    public Long getUsersCount() {
        return repository.count();
    }

    @Override
    public User logicalRemoveWithReturn(Long id) throws NoContentException {
        User user = repository.findUserByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active User Was Found with id  " + id  +" To Remove !")
        );
        user.setDeleted(true);
        return repository.save(user);
    }


    @Override
    public List<User> findUserByDeletedFalse() {
        return repository.findUserByDeletedFalse();
    }

    @Override
    public Optional<User> findUserByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<User> optional = repository.findUserByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active User Was Found with id : " + id );
        }
    }

    @Override
    public Optional<User> findUserByUsernameAndDeletedFalse(String username) throws NoContentException {
        Optional<User> optional = repository.findUserByUsernameAndDeletedFalse(username);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active User Was Found with username : " + username );
        }
    }
    @Override
    public boolean existsUserByUsernameAndDeletedFalse(String username) throws NoUserException {
        if (repository.existsUserByUsernameAndDeletedFalse(username)){
            return existsUserByUsernameAndDeletedFalse(username);

        }else {
            throw new NoUserException("No User Was Found !");
        }
    }


    @Override
    public Long countByDeletedFalse() {
        return repository.countByDeletedFalse();
    }
}
