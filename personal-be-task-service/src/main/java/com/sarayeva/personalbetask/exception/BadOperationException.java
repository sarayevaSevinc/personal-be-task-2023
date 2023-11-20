package com.sarayeva.personalbetask.exception;

import static com.sarayeva.personalbetask.constant.Constants.BAD_OPERATION_ERROR_MESSAGE;

public class BadOperationException extends RuntimeException {

  public BadOperationException() {
    super(BAD_OPERATION_ERROR_MESSAGE);
  }
}
