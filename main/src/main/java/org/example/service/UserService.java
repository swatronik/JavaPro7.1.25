package org.example.service;

import org.example.dao.UserDao;
import org.example.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    public Long insertUser(String username) {
        return userDao.insertUser(username);
    }

    public void updateUser(Long id, String username) {
        userDao.updateUser(id, username);
    }

    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}
