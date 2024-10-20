package com.springapplication.userapp.controller;

import com.springapplication.userapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.springapplication.userapp.controller.model.NewUserDTO;

@Component
class RegisterMapper {

    @Autowired
    PasswordEncoder passwordEncoder;

    public User mapper(NewUserDTO userDTO) {


        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return user;

    }

}
