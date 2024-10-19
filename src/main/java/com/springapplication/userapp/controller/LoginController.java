package com.springapplication.userapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.springapplication.userapp.controller.api.LoginApiDelegate;

@Component
public class LoginController implements  LoginApiDelegate {

    @Override
    public ResponseEntity<String> login() {
        return new ResponseEntity<>("login", HttpStatus.OK);
    }
}
