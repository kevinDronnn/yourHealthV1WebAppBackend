package com.example.yourhealthv1.service;

import com.example.yourhealthv1.entity.AuthenticationResponse;
import com.example.yourhealthv1.entity.Users;
import com.example.yourhealthv1.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(Users request) {

        if ((repository.findUsersByName(request.getUsername()).isPresent() || repository.findUsersByEmail(request.getEmail()).isPresent()) && !Objects.equals(request.getUsername(), "anonymousUser")) {
            throw new AuthenticationServiceException("Username already exists");
        }

        Users user = new Users();
        user.setName(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole("USER");

        user = repository.save(user);

        String jwt = jwtService.getToken(user);

        return new AuthenticationResponse(jwt, "User registration was successful");

    }

    public AuthenticationResponse authenticate(Users request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Users user = repository.findUsersByName(request.getUsername()).orElseThrow();
        String jwt = jwtService.getToken(user);

        return new AuthenticationResponse(jwt, "User login was successful");

    }

    public String findEmailOfUser(String username) {
        return repository.findEmailByName(username);
    }

    public void updatePassword(String newPassword, String oldPassword, String email, String name) {
        Users user = repository.findUsersByNameAndEmail(name, email);
        if ((newPassword != null || newPassword.length() != 0)
                && passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            repository.save(user);
        } else {
            throw new NoSuchElementException();
        }
    }
}
