package ru.ilya.shopcraftercore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(EmailValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEmailValidationException(EmailValidationException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadCredentialsException(BadCredentialsException ex) {
        return ex.getMessage();
    }
}
