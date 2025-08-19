package com.tempjunior.book_jesus_application.infra.exceptions;

public class LivroValidatorException extends RuntimeException{
    public LivroValidatorException(String message){
        super(message);
    }

    public LivroValidatorException(String message, Throwable cause){
        super(message, cause);
    }
}
