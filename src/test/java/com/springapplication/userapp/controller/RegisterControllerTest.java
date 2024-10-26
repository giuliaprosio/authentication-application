package com.springapplication.userapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springapplication.userapp.config.CustomAuthenticationSuccessHandler;
import com.springapplication.userapp.model.User;
import com.springapplication.userapp.service.UserDetailsServiceImpl;
import com.springapplication.userapp.utils.RegisterRequestObjectMother;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import com.springapplication.userapp.controller.model.NewUserDTO;
import com.springapplication.userapp.controller.api.RegisterApiController;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {RegisterApiController.class})
@Import(RegisterController.class)
public class RegisterControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RegisterValidator registerValidator;

    @MockBean
    RegisterMapper registerMapper;

    @MockBean
    UserDetailsServiceImpl userDetailsService;

    @MockBean
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    private static final String ENDPOINT = "/register";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void GetRegister_ReturnsRegister() throws Exception {

        MvcResult response = this.mockMvc.perform(get(ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(content().string("register"))
                .andReturn();


    }

    @Test
    public void PostValidRegister_ReturnsAdded() throws Exception {

        // given
        var registerRequest = RegisterRequestObjectMother.makeValidUserDTO();
        var userMapped = RegisterRequestObjectMother.makeValidUserFromDTO(registerRequest);

        // when
        when(registerValidator.validation(any(NewUserDTO.class)))
                .thenReturn(Either.right(registerRequest));
        when(registerMapper.mapper(any(NewUserDTO.class)))
                .thenReturn(userMapped);
        when(userDetailsService.registerUser(any(User.class)))
                .thenReturn(Either.right(userMapped));

        String requestBody = objectMapper.writeValueAsString(registerRequest);
        ResultActions result = this.mockMvc
                .perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        // then
        result.andExpect(status().isOk())
              .andExpect(content().string("added"));

    }
}
