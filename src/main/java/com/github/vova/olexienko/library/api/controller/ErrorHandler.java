package com.github.vova.olexienko.library.api.controller;

import com.github.vova.olexienko.library.api.dto.ErrorDto;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    private static final String DEFAULT_ERROR_MESSAGE = "Сталася непередбачувана помилка, спробуйте будь-ласка ще раз пізніше";
    private static final String CONSTRAINT_VIOLATION_ERROR_MESSAGE = "Неможливо виконати операцію: дана сутність пов'язана з іншими в базі даних";
    private static final String AUTH_ERROR_MESSAGE = "Не правильні дані для входу, спробуйте ще раз";

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ErrorDto exception(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        return new ErrorDto(DEFAULT_ERROR_MESSAGE, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class, HttpRequestMethodNotSupportedException.class, MethodArgumentNotValidException.class, ValidationException.class})
    public ErrorDto validationException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ErrorDto(DEFAULT_ERROR_MESSAGE, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public ErrorDto authException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ErrorDto(AUTH_ERROR_MESSAGE, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorDto databaseException(Exception ex) {
        log.error(ex.getMessage(), ex);
        if (ex.getCause() instanceof ConstraintViolationException) {
            return new ErrorDto(CONSTRAINT_VIOLATION_ERROR_MESSAGE, ex.getMessage());
        }
        return new ErrorDto(DEFAULT_ERROR_MESSAGE, ex.getMessage());
    }
}