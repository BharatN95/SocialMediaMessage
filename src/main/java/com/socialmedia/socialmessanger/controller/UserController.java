package com.socialmedia.socialmessanger.controller;

import com.socialmedia.socialmessanger.models.User;
import com.socialmedia.socialmessanger.repository.UserRepository;
import com.socialmedia.socialmessanger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository UserRepository;

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user){

        User savedUser = userService.registeredUser(user);

        return savedUser;
    }

    @GetMapping("/users")
    public List<User>getUsers(){
        List<User> users = UserRepository.findAll();
        return users;
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception {

        User user = userService.findUserById(id);
        return user;
    }

    @PutMapping("/users/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable Integer userId) throws Exception {

        User updatedUser = userService.updateUser(user, userId);

    return updatedUser;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) throws Exception {

        Optional<User> user = UserRepository.findById(userId);

        if(user.isEmpty()){
            throw new Exception("User not exist with id" + userId);
        }

        UserRepository.delete(user.get());
        return "User Deleted Successfully with id" + userId;
    }

    @PutMapping("/users/follow/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {
        User user = userService.followUser(userId1, userId2);
        return user;
    }

    @GetMapping("/users/search")
    public List<User> searchUser(@RequestParam("query") String query){
        List<User> users = userService.searchUser(query);

        return users;
    }
}

