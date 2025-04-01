package ru.ilya.shopcraftercore.exception;

public class EmailValidationException extends RuntimeException {
    public EmailValidationException() {
        super("Email validation failed");
    }
}
