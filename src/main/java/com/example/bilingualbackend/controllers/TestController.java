package com.example.bilingualbackend.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String testAuth(){
        return "Everything is okay!";
    }
}
