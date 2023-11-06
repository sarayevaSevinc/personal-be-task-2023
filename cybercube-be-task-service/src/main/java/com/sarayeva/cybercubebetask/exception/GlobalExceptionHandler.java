package com.sarayeva.cybercubebetask.exception;

import com.sarayeva.cybercubebetask.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = {ProfileNotFoundException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  protected ErrorResponseDto handleProfileNotFoundException(
      ProfileNotFoundException ex) {
    return ErrorResponseDto.builder()
        .message(ex.getMessage())
        .build();
  }

  @ExceptionHandler(value = {AnalysisNotFoundException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  protected ErrorResponseDto handleAnalysisNotFoundException(
      AnalysisNotFoundException ex) {
    return ErrorResponseDto.builder()
        .message(ex.getMessage())
        .build();
  }

  @ExceptionHandler(value = {InsufficientBudgetException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  protected ErrorResponseDto handleInsufficientBudgetException(
      InsufficientBudgetException ex) {
    return ErrorResponseDto.builder()
        .message(ex.getMessage())
        .build();
  }
}
