package com.springapplication.userapp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryRewriter;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    //query methods
    @Query("select count(u) = 1 from User u where u.username = ?1 and u.password = ?2")
    Boolean findExistingUser(String username, String hashedPassword);



}
