package com.example.marketmanagementapi.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String mensaje) {
        super(mensaje);
    }
}
