package com.tempjunior.book_jesus_application.infra.exceptions;

public class EmprestimoValidatorException extends RuntimeException{
    public EmprestimoValidatorException(String message){
        super(message);
    }

    public EmprestimoValidatorException(String message, Throwable cause){
        super(message, cause);
    }
}
