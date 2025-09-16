package org.example.service;

import org.example.repository.UserRepository;
import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserService() {
    }


    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUser(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Long insertUser(String username) {
        return repository.save(new User(username)).getId();
    }

    public void updateUser(Long id, String username) {
        repository.save(new User(id, username));
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
