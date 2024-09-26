package com.springapplication.userapp.service;

import com.springapplication.userapp.func.Either;
import com.springapplication.userapp.model.User;
import com.springapplication.userapp.model.UserError;
import com.springapplication.userapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public Either<UserError, User> registerUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return Either.left(new UserError.NoUsername());
        }

        if (userRepository.findByUsername(user.getUsername()) != null) {
            return Either.left(new UserError.DuplicatedUsername());
        }

        if(user.getEmail() == null) {
            return Either.left(new UserError.NoEmail());
        }

        if(userRepository.findByEmail(user.getEmail()) != null) {
            return Either.left(new UserError.EmailAlreadyInSystem());
        }

        if(user.getPassword() == null) {
            return Either.left(new UserError.NoPassword());
        }

        if(user.getSecondPassword() == null) {
            return Either.left(new UserError.NoSecondPassword());
        }

        if(!user.getPassword().equals(user.getSecondPassword())) {
            return Either.left(new UserError.SecondPasswordNoMatch());
        }

        userRepository.save(user);

        return Either.right(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Fetch user from repository
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(user);
    }
}
