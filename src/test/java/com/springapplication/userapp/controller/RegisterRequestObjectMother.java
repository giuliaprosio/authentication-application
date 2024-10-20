package com.springapplication.userapp.controller;

import com.springapplication.userapp.controller.model.NewUserDTO;
import com.springapplication.userapp.model.User;

public class RegisterRequestObjectMother {

    static NewUserDTO makeValidUserDTO() {

        var userDTO = new NewUserDTO();
        userDTO.setUsername("username");
        userDTO.setEmail("email");
        userDTO.setPassword("password");
        userDTO.setSecondPassword("password");

        return userDTO;

    }

    static NewUserDTO userDTOMissingName() {

        var userDTO = makeValidUserDTO();
        userDTO.setUsername(null);

        return userDTO;
    }

    static NewUserDTO userDTOMissingEmail() {

        var userDTO = makeValidUserDTO();
        userDTO.setEmail(null);

        return userDTO;
    }

    static NewUserDTO userDTOMissingPassword() {

        var userDTO = makeValidUserDTO();
        userDTO.setPassword(null);

        return userDTO;
    }

    static NewUserDTO userDTOMissingSecondPassword() {

        var userDTO = makeValidUserDTO();
        userDTO.setSecondPassword(null);

        return userDTO;
    }

    static NewUserDTO userDTOPasswordsNotMatching() {

        var userDTO = makeValidUserDTO();
        userDTO.setSecondPassword("differentPassword");

        return userDTO;
    }

    static User makeValidUserFromDTO(NewUserDTO userDTO) {

        var user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        return user;
    }
}
