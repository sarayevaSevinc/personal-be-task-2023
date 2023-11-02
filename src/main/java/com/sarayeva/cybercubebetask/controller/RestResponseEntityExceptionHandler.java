package com.sarayeva.cybercubebetask.controller;

import com.sarayeva.cybercubebetask.dto.ErrorResponseDto;
import com.sarayeva.cybercubebetask.exception.AnalysisNotFoundException;
import com.sarayeva.cybercubebetask.exception.InsufficientBudgetException;
import com.sarayeva.cybercubebetask.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(value = {UserNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ResponseEntity<ErrorResponseDto> handleUserNotFoundException(
            UserNotFoundException ex, WebRequest request) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .errorCode(404)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponseDto, null, 404);
    }

    @ExceptionHandler(value = {AnalysisNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleAnalysisNotFoundException(
            AnalysisNotFoundException ex, WebRequest request) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .errorCode(404)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponseDto, null, 404);
    }

    @ExceptionHandler(value = {InsufficientBudgetException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleInsufficientBudgetException(
            InsufficientBudgetException ex, WebRequest request) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .errorCode(400)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponseDto, null, 400);
    }
}
