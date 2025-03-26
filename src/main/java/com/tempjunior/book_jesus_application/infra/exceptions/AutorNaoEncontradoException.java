package com.tempjunior.book_jesus_application.infra.exceptions;

public class AutorNaoEncontradoException extends RuntimeException{

    public AutorNaoEncontradoException(String message){
        super(message);
    }
}
