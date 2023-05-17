package com.example.bilingualbackend.controllers;

import com.example.bilingualbackend.db.services.AuthenticationService;
import com.example.bilingualbackend.dto.requests.auth.AuthenticationRequest;
import com.example.bilingualbackend.dto.requests.auth.SignUpRequest;
import com.example.bilingualbackend.dto.responses.auth.AuthenticationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public AuthenticationResponse signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        return authenticationService.signUp(signUpRequest);
    }

    @PostMapping("/sign-in")
    public AuthenticationResponse signIn(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        return authenticationService.signIn(authenticationRequest);
    }
}