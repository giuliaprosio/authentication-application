package com.springapplication.userapp.controller;

import com.springapplication.userapp.func.Either;
import com.springapplication.userapp.model.User;
import com.springapplication.userapp.model.UserError;
import com.springapplication.userapp.service.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.springapplication.userapp.controller.api.RegisterApiDelegate;
import com.springapplication.userapp.controller.model.NewUserDTO;

@Component
public class RegisterController implements RegisterApiDelegate {

    private final UserDetailsServiceImpl userDetailsService;
    private final RegisterValidator registerValidator;
    private final RegisterMapper registerMapper;

    public RegisterController(UserDetailsServiceImpl userDetailsService, RegisterValidator registerValidator, RegisterMapper registerMapper) {
        this.userDetailsService = userDetailsService;
        this.registerValidator = registerValidator;
        this.registerMapper = registerMapper;
    }

    @Override
    public ResponseEntity<String> register() {
        return new ResponseEntity<>("register", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> submission(NewUserDTO userDTO) {

        Either<UserError, NewUserDTO> validationResult = registerValidator.validation(userDTO);
        User mappedUser = registerMapper.mapper(validationResult.right());


        Either<UserError, User> result = userDetailsService.registerUser(mappedUser);

        if(result.isLeft()) {
            return new ResponseEntity<>(result.left().toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("added", HttpStatus.OK);
    }

}