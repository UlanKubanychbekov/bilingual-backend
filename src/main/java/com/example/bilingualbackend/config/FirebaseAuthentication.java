package com.example.bilingualbackend.config;

import com.example.bilingualbackend.config.jwt.JwtService;
import com.example.bilingualbackend.db.entities.User;
import com.example.bilingualbackend.db.enums.Role;
import com.example.bilingualbackend.db.repositories.UserRepository;
import com.example.bilingualbackend.dto.responses.auth.AuthenticationResponse;
import com.example.bilingualbackend.exceptions.NotFoundException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

import javax.annotation.PostConstruct;

@Service
@Slf4j
@RequiredArgsConstructor
public class FirebaseAuthentication {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    @PostConstruct
    void init() {
        try {
            GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ClassPathResource("bilingual-backend.json").getInputStream());
            FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                    .setCredentials(googleCredentials)
                    .build();
            log.info("Firebase initialized successfully");
            FirebaseApp firebaseApp = FirebaseApp.initializeApp(firebaseOptions);
        } catch (IOException e) {
            log.error("IOException occurred while initializing Firebase");
        }
    }

    public AuthenticationResponse authWithGoogle(String tokenId) throws FirebaseAuthException {
        FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(tokenId);
        if (!userRepository.existsByEmail(firebaseToken.getEmail())) {
            User newUser = new User();
            String[] name = firebaseToken.getName().split(" ");
            newUser.setFirstName(name[0]);
            newUser.setLastName(name[1]);
            newUser.setEmail(firebaseToken.getEmail());
            newUser.setPassword(firebaseToken.getEmail());
            newUser.setRole(Role.USER);
            userRepository.save(newUser);
        }
        User user = userRepository.findByEmail(firebaseToken.getEmail()).orElseThrow(() -> {
            log.error("User not found for email {}", firebaseToken.getEmail());
            return new NotFoundException(String.format("User with this %s email not found !!", firebaseToken.getEmail()));
        });
        log.info("User authenticated successfully with Google for email {}", firebaseToken.getEmail());
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .email(firebaseToken.getEmail())
                .token(token)
                .build();
    }
}