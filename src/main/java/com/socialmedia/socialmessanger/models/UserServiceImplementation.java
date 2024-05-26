package com.socialmedia.socialmessanger.models;

import com.socialmedia.socialmessanger.repository.UserRepository;
import com.socialmedia.socialmessanger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository UserRepository;
    @Override
    public User registeredUser(User user) {
            User newUser = new User();
            newUser.setId(user.getId());
            newUser.setFirstNAme(user.getFirstNAme());
            newUser.setLastName(user.getLastName());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());

            User savedUser = UserRepository.save(newUser);

            return savedUser;
    }

    @Override
    public User findById(Integer userId) throws Exception {

        Optional<User> user = UserRepository.findById(userId);

        if (user.isPresent()){
            return user.get();
        }

        throw new Exception("User doesn't exist with userid " + userId);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = UserRepository.findByEmail(email);
        return user;
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        User user = UserRepository.findUserById(userId);
        return user;
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws Exception {

        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());

        UserRepository.save(user1);
        UserRepository.save(user2);


        return user1;
    }



    @Override
    public User updateUser(User user, Integer userId) throws Exception {
        Optional<User> user1 = UserRepository.findById(userId);

        if(user1.isEmpty()){
            throw new Exception("User not exist with id" + userId);
        }


        User oldUser = user1.get();

        if (user.getFirstNAme()!=null){
            oldUser.setFirstNAme(user.getFirstNAme());
        }
        if (user.getLastName()!=null){
            oldUser.setLastName(user.getLastName());
        }
        if (user.getEmail()!=null){
            oldUser.setEmail(user.getEmail());
        }

        User updatedUser = UserRepository.save(oldUser);

        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {


        return UserRepository.searchUser(query);
    }



}
