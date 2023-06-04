package com.example.bilingualbackend.controllers.restControllers;

import com.example.bilingualbackend.db.services.AuthenticationService;
import com.example.bilingualbackend.dto.requests.AuthenticationRequest;
import com.example.bilingualbackend.dto.requests.SignUpRequest;
import com.example.bilingualbackend.dto.responses.AuthenticationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public AuthenticationResponse signIn(@RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.signIn(authenticationRequest);
    }
//    @PostMapping("/google")
//    public AuthenticationResponse authenticateWithGoogle(@RequestBody String tokenId) {
//        return authenticationService.authWithGoogle(tokenId);
//    }

}