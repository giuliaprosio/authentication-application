package com.springapplication.userapp.config;

import com.springapplication.userapp.service.CustomUserDetails;
import com.springapplication.userapp.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }


    @Bean //or filterChain
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider());

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/static/**", "/assets/**").permitAll()
                        .requestMatchers("/login/**", "/register").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/home")
                                .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/").permitAll());
        return http.build();
        /*
        return http
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/css/**", "/static/**", "/assets/**").permitAll()
                        .requestMatchers("/login/**", "/register").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login"))
                .userDetailsService(userDetailsService)
                .build();

                /*.formLogin(form ->
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
                .build();*/

    }


}
