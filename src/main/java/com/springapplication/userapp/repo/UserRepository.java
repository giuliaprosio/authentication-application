package com.springapplication.userapp.repo;

import com.springapplication.userapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //query methods
    //@Query("select count(u) = 1 from User u where u.username = ?1 and u.password = ?2")
    //Boolean findExistingUser(String username, String hashedPassword);


    Optional<User> findByUsername(String username);
}
