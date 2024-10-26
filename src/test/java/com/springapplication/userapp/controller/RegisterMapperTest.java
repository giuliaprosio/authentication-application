package com.springapplication.userapp.controller;

import com.springapplication.userapp.model.User;
import com.springapplication.userapp.utils.RegisterRequestObjectMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.springapplication.userapp.controller.model.NewUserDTO;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RegisterMapperTest {

    @MockBean
    PasswordEncoder passwordEncoder;

    @InjectMocks
    RegisterMapper registerMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void receiveUserDTO_mapToUser() {

        // given
        NewUserDTO userDTO = RegisterRequestObjectMother.makeValidUserDTO();
        User user = RegisterRequestObjectMother.makeValidUserFromDTO(userDTO);

        // when
        when(passwordEncoder.encode(any(String.class)))
                .thenReturn("password");
        var result = registerMapper.mapper(userDTO);

        Assertions.assertEquals(user.getUsername(), result.get().getUsername());
        Assertions.assertEquals(user.getEmail(), result.get().getEmail());
        Assertions.assertEquals(user.getPassword(), result.get().getPassword());

    }

}
