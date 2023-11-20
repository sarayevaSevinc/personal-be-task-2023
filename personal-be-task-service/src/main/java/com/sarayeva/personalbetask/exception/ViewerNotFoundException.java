package com.sarayeva.personalbetask.exception;

import static com.sarayeva.personalbetask.constant.Constants.VIEWER_NOT_FOUND_ERROR_MESSAGE;

public class ViewerNotFoundException extends RuntimeException {

  public ViewerNotFoundException() {
    super(VIEWER_NOT_FOUND_ERROR_MESSAGE);
  }
}
