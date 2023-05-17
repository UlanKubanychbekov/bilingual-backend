package com.example.bilingualbackend.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String massage){
        super(massage);
    }
}