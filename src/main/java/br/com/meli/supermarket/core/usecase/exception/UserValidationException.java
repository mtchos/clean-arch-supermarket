package br.com.archdemo.supermarket.core.usecase.exception;

public class UserValidationException extends RuntimeException {

    public UserValidationException(final String message) {
        super(message);
    }
}
