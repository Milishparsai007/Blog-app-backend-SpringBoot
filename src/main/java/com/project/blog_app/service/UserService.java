package com.project.blog_app.service;

import com.project.blog_app.payload.UserDataTransfer;

import java.util.List;

public interface UserService {
    UserDataTransfer createUser(UserDataTransfer user);

    UserDataTransfer updateUser(UserDataTransfer user, Integer userId);
    UserDataTransfer getUserById(Integer userId);
    List<UserDataTransfer> getAllUsers();
    void deleteUser(Integer userId);

}
