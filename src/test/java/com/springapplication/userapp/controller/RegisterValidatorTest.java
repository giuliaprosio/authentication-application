package com.springapplication.userapp.controller;

import com.springapplication.userapp.utils.RegisterRequestObjectMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RegisterValidatorTest {

    @Autowired
    RegisterValidator registerValidator;

    @Test
    public void validUserDTO_returnsUserDTO() {

        // given
        var userDTO = RegisterRequestObjectMother.makeValidUserDTO();

        // when
        var result = registerValidator.validation(userDTO);

        //then
        Assertions.assertEquals(userDTO.getUsername(), result.right().getUsername());
        Assertions.assertEquals(userDTO.getEmail(), result.right().getEmail());
        Assertions.assertEquals(userDTO.getPassword(), result.right().getPassword());
        Assertions.assertEquals(userDTO.getSecondPassword(), result.right().getSecondPassword());

    }

    @Test
    public void missingUsername_returnsError() {
        // given
        var userDTO = RegisterRequestObjectMother.userDTOMissingName();

        // when
        var result = registerValidator.validation(userDTO);

        // then
        Assertions.assertEquals("Username is required.", result.left().toString());

    }

    @Test
    public void missingEmail_returnsError() {
        // given
        var userDTO = RegisterRequestObjectMother.userDTOMissingEmail();

        // when
        var result = registerValidator.validation(userDTO);

        // then
        Assertions.assertEquals("Email required.", result.left().toString());

    }

    @Test
    public void missingPassword_returnsError() {
        // given
        var userDTO = RegisterRequestObjectMother.userDTOMissingPassword();

        // when
        var result = registerValidator.validation(userDTO);

        // then
        Assertions.assertEquals("Password required.", result.left().toString());

    }

    @Test
    public void missingSecondPassword_returnsError() {
        // given
        var userDTO = RegisterRequestObjectMother.userDTOMissingSecondPassword();

        // when
        var result = registerValidator.validation(userDTO);

        // then
        Assertions.assertEquals("Second password check is required.", result.left().toString());

    }

    @Test
    public void passwordsNotMatching_returnsError() {
        // given
        var userDTO = RegisterRequestObjectMother.userDTOPasswordsNotMatching();

        // when
        var result = registerValidator.validation(userDTO);

        // then
        Assertions.assertEquals("The passwords are not matching.", result.left().toString());
    }

}