package com.springapplication.userapp.service;

import com.springapplication.userapp.model.User;
import com.springapplication.userapp.model.UserObjectMother;
import com.springapplication.userapp.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailsServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void givenValidCredentials_whenRegisterUser_thenSaveInRepo() {
        var user = UserObjectMother.createValidUser();

        var result = userDetailsService.registerUser(user);

        Assertions.assertEquals(user, result.get());
    }

    @Test
    public void givenValidUsername_whenLoadUser_thenReturnUser() {
        var user = UserObjectMother.createValidUser();

        when(userRepository.findByUsername(anyString())).thenReturn(user);
        var result = userDetailsService.loadUserByUsername(user.getUsername());

        Assertions.assertEquals(user.getUsername(), result.getUsername());

    }

}
