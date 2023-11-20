package com.sarayeva.personalbetask.exception;

import static com.sarayeva.personalbetask.constant.Constants.ANALYSIS_TYPE_VALIDATION_ERROR_MESSAGE;

public class AnalysisTypeValidationException extends RuntimeException {

  public AnalysisTypeValidationException() {
    super(ANALYSIS_TYPE_VALIDATION_ERROR_MESSAGE);
  }
}
