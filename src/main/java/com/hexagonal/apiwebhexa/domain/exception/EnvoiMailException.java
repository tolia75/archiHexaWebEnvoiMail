package com.hexagonal.apiwebhexa.domain.exception;

public class EnvoiMailException extends RuntimeException {

    public EnvoiMailException(String message) {
        super(message);
    }
}
