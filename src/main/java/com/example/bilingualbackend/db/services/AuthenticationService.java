package com.example.bilingualbackend.db.services;

import com.example.bilingualbackend.config.jwt.JwtService;
import com.example.bilingualbackend.db.enums.Role;
import com.example.bilingualbackend.dto.requests.auth.AuthenticationRequest;
import com.example.bilingualbackend.dto.requests.auth.SignUpRequest;
import com.example.bilingualbackend.dto.responses.auth.AuthenticationResponse;
import com.example.bilingualbackend.exceptions.AlreadyExistException;
import com.example.bilingualbackend.exceptions.BadCredentialException;
import com.example.bilingualbackend.exceptions.BadRequestException;
import com.example.bilingualbackend.exceptions.NotFoundException;
import com.example.bilingualbackend.db.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.bilingualbackend.db.entities.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AlreadyExistException("Sorry, this email is already registered. Please try a different email or login to your existing account");
        }

        User user = User.builder()
                .email(signUpRequest.getEmail())
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public AuthenticationResponse signIn(AuthenticationRequest authenticationRequest) {

        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(
                () -> new NotFoundException("User with this email: " + authenticationRequest.getEmail() + " not found!")
        );

        if (authenticationRequest.getPassword().isBlank()) {
            throw new BadRequestException("password can not be empty!");
        }

        if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialException("incorrect password!");
        }

        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwt)
                .email(user.getUsername())
                .role(user.getRole())
                .build();
    }
}