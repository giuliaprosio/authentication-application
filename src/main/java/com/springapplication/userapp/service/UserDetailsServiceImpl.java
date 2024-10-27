package com.springapplication.userapp.service;

import com.springapplication.userapp.model.User;
import com.springapplication.userapp.model.UserError;
import com.springapplication.userapp.repo.UserRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
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

        Either<Throwable, User> result =
                Try.of(() -> userRepository.findByUsername(user.getUsername()))
                                .toEither();

        if(result.get() != null) return Either.left(new UserError.DuplicatedUsername());

        Either<Throwable, User> result2 =
                Try.of(() -> userRepository.findByEmail(user.getEmail()))
                                .toEither();

        if(result2.get() != null) return Either.left(new UserError.EmailAlreadyInSystem());

        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        userRepository.save(user);

        return Either.right(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Try<User> user = Try.of(() -> userRepository.findByUsername(username));

        return user.map(CustomUserDetails::new)
                .getOrElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}
