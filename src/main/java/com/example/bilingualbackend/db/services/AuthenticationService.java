package com.example.bilingualbackend.db.services;

import com.example.bilingualbackend.config.FirebaseAuthentication;
import com.example.bilingualbackend.config.jwt.JwtService;
import com.example.bilingualbackend.db.entities.User;
import com.example.bilingualbackend.db.enums.Role;
import com.example.bilingualbackend.db.services.repositories.UserRepository;
import com.example.bilingualbackend.dto.requests.AuthenticationRequest;
import com.example.bilingualbackend.dto.requests.SignUpRequest;
import com.example.bilingualbackend.dto.responses.AuthenticationResponse;
import com.example.bilingualbackend.exceptions.AlreadyExistException;
import com.example.bilingualbackend.exceptions.BadCredentialException;
import com.example.bilingualbackend.exceptions.BadRequestException;
import com.example.bilingualbackend.exceptions.NotFoundException;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final FirebaseAuthentication firebaseAuthentication;

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

    public AuthenticationResponse authWithGoogle(String tokenId) {
        try {
            return firebaseAuthentication.authWithGoogle(tokenId);
        } catch (FirebaseAuthException e) {
            log.error("Firebase authentication error: {}", e.getMessage());
            throw new NotFoundException("Firebase authentication error");
        }
    }
}