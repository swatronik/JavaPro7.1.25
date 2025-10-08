package org.example.payment.service;

import org.example.payment.repository.UserRepository;
import org.example.payment.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserService() {
    }

    public List<UserEntity> getAllUsers() {
        return repository.findAll();
    }

    public UserEntity getUser(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Long insertUser(String username) {
        return repository.save(new UserEntity(username)).getId();
    }

    public void updateUser(Long id, String username) {
        repository.save(new UserEntity(id, username));
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
