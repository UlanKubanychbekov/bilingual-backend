package com.example.bilingualbackend.exceptions;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String massage){
        super(massage);
    }
}