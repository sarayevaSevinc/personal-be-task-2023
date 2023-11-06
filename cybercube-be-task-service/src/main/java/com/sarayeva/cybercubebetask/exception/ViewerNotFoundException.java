package com.sarayeva.cybercubebetask.exception;

import static com.sarayeva.cybercubebetask.constant.Constants.VIEWER_NOT_FOUND_ERROR_MESSAGE;

public class ViewerNotFoundException extends RuntimeException {

  public ViewerNotFoundException() {
    super(VIEWER_NOT_FOUND_ERROR_MESSAGE);
  }
}
