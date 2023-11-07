package com.sarayeva.cybercubebetask.exception;

import static com.sarayeva.cybercubebetask.constant.Constants.BAD_OPERATION_ERROR_MESSAGE;

public class BadOperationException extends RuntimeException {

  public BadOperationException() {
    super(BAD_OPERATION_ERROR_MESSAGE);
  }
}
