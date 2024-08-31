package com.springapplication.userapp.config;

import com.springapplication.userapp.repo.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private JpaUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                //.csrf(csrf -> csrf.disable())
                //.cors(cors -> cors.disable())
                .formLogin(form ->
                                form.loginPage("/login")
                                        .defaultSuccessUrl("/home", true)
                                        .permitAll()

                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/resources/**", "/login", "/register").permitAll()
                        .anyRequest().permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/home")
                        .permitAll()
                )
                .build();

    }



}
