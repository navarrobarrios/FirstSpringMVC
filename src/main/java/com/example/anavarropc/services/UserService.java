package com.example.anavarropc.services;

import com.example.anavarropc.beans.User;
import com.example.anavarropc.interfaces.IUserDAO;
import com.example.anavarropc.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDAO userDAO;

    @Override
    public List<User> getAllUsers() {
        return  userDAO.getAllUsers();
    }

    @Override
    public User getUserById(Integer id) {
        User userFound =  userDAO.getUserById(id);
        return userFound;
    }

    @Override
    public User getUserByUsername(String username) {
        User userFound = userDAO.getUserByUsername(username);
        return userFound;
    }

    @Override
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }
}
