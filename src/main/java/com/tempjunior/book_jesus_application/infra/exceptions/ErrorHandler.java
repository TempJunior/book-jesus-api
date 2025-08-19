package com.tempjunior.book_jesus_application.infra.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserNotFoundException>trataErroUsuarioNaoEncontrado(UserNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(EmprestimoValidatorException.class)
    public ResponseEntity<String> tratarErroEmprestimos(EmprestimoValidatorException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(LivroValidatorException.class)
    public ResponseEntity<String> tratarErroEmprestimos(LivroValidatorException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
