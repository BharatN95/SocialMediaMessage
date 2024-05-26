package com.socialmedia.socialmessanger.service;

import com.socialmedia.socialmessanger.models.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    public User registeredUser(User user);

    public User findById(Integer userId) throws Exception;

    public User findUserByEmail(String email);

    public User findUserById(Integer userId) throws Exception;

    public User followUser(Integer userId1, Integer userId2) throws Exception;

    public User updateUser(User user, Integer userId) throws Exception;

//    public List<User> searchUser = new ArrayList<>();

    List<User> searchUser(String query);
}
