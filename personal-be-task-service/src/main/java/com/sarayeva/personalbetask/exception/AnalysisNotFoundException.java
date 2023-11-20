package com.sarayeva.personalbetask.exception;

import static com.sarayeva.personalbetask.constant.Constants.ANALYSIS_NOT_FOUND_ERROR_MESSAGE;

public class AnalysisNotFoundException extends RuntimeException {

  public AnalysisNotFoundException() {
    super(ANALYSIS_NOT_FOUND_ERROR_MESSAGE);
  }
}
