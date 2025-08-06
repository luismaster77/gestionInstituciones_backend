package com.co.lep.gestion.estudiantes.exepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.co.lep.gestion.estudiantes.constantes.Mensajes;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegistroNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleRegistroNoEncontradoException(RegistroNoEncontradoException ex) {
        ErrorResponse errorResponse = new ErrorResponse(Mensajes.TXT_REGISTRO_NOT_FOUND, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(Mensajes.TXT_REGISTRO_NOT_FOUND, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}