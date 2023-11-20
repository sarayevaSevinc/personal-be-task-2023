package com.sarayeva.personalbetask.exception;

import static com.sarayeva.personalbetask.constant.Constants.PROFILE_NOT_FOUND_ERROR_MESSAGE;

public class ProfileNotFoundException extends RuntimeException {

  public ProfileNotFoundException() {
    super(PROFILE_NOT_FOUND_ERROR_MESSAGE);
  }
}
