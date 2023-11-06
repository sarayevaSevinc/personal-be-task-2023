package com.sarayeva.cybercubebetask.exception;

import static com.sarayeva.cybercubebetask.constant.Constants.PROFILE_NOT_FOUND_ERROR_MESSAGE;

public class ProfileNotFoundException extends RuntimeException {

  public ProfileNotFoundException() {
    super(PROFILE_NOT_FOUND_ERROR_MESSAGE);
  }
}
