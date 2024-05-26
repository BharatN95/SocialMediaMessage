package com.socialmedia.socialmessanger.repository;

import com.socialmedia.socialmessanger.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email);

    public User findUserById(Integer id);

    @Query("select u from User u where u.firstNAme like %:query% OR u.lastName LIKE %:query% OR u.email LIKE %:query%")
    public List<User> searchUser(@Param("query") String query);

}
