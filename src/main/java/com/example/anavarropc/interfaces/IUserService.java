package com.example.anavarropc.interfaces;


import com.example.anavarropc.beans.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();

    User getUserById(Integer id);

    User getUserByUsername(String username);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
