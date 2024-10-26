package com.springapplication.userapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    private String username;

    @Column(nullable = false, unique = true)
    @NotNull
    private String email;

    @Column(nullable = false)
    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}

/* TODO:
    - add test for other classes after controller (also research how to/what makes sense to test)
    - put the sensible information (passwords etc) in an .env file and make sure the file is not visible
        but also everything works correctly
    - research possible vulnerabilities in your code and how to handle them
    - add some more errors to be handled (all the network errors etc)
    - rewrite the readme file
    - see what's wrong with maven
    - use vavr to make it functional (remove custom Either class)
    - add loggers and clean code (some controllers might not need to be there)
    - add admin role (add in db username admin and a password I store, generally
    users have role user but if in login username is admin then I have more privileges)
    .
    - after finishing with backend, FRONTEND:
    - probably best way to start is to look up tutorial on frontend
    - test more the back-end/front-end interaction
    - better up the frontend (this is the least specific because it is the part I know less about)
    .
    - infrastructure:
    - kubernetes (jkube) and docker are the number 1 question
    - next: docker, makefile to run the app
    .
    - VAVR:
    - make also returning types of database with vavr (optional, seq)
 */

