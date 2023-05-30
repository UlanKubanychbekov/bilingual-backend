package com.example.bilingualbackend.controllers.graphqlControllers;

import com.example.bilingualbackend.db.services.AuthenticationService;
import com.example.bilingualbackend.dto.requests.auth.AuthenticationRequest;
import com.example.bilingualbackend.dto.requests.auth.SignUpRequest;
import com.example.bilingualbackend.dto.responses.auth.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SecurityController {
    private final AuthenticationService authenticationService;

    @MutationMapping(name = "signUp")
    AuthenticationResponse signUp(@Argument String email, @Argument String firstName, @Argument String lastName, @Argument String password) {
        return authenticationService.signUp(new SignUpRequest(firstName, lastName, email, password));
    }

    @MutationMapping(name = "signIn")
    AuthenticationResponse signIn(@Argument String email, @Argument String password) {
        return authenticationService.signIn(new AuthenticationRequest(email, password));
    }
}