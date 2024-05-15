package com.example.marketmanagementapi.exception;

import com.example.marketmanagementapi.model.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageDto> noEncontrado(NotFoundException nfe) {
        MessageDto errorDto = new MessageDto(nfe.getMessage(), 404);
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
