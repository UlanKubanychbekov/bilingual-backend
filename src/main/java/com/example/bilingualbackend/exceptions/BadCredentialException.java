package com.example.bilingualbackend.exceptions;

public class BadCredentialException extends RuntimeException{
    public BadCredentialException(String massage){
        super(massage);
    }
}