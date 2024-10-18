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

    @Transient
    private String secondPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(String secondPassword) {
        this.secondPassword = secondPassword;
    }

}

/* TODO:
    - add tests for all the classes (good practice)
    - use openAPI to generate the controllers (documentations, dtos and ease to use postman)
    - test more the back-end/front-end interaction
    - put the sensible information (passwords etc) in an .env file and make sure the file is not visible
        but also everything works correctly
    - research possible vulnerabilities in your code and how to handle them
    - add some more errors to be handled
    - rewrite the readme file
    - do some UML/sequence diagrams
    - better up the frontend (this is the least specific because it is the part I know less about)
    - next: docker, makefile to run the app
 */

