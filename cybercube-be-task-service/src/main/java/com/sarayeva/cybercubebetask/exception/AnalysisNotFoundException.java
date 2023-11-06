package com.sarayeva.cybercubebetask.exception;

import static com.sarayeva.cybercubebetask.constant.Constants.ANALYSIS_NOT_FOUND_ERROR_MESSAGE;

public class AnalysisNotFoundException extends RuntimeException {

  public AnalysisNotFoundException() {
    super(ANALYSIS_NOT_FOUND_ERROR_MESSAGE);
  }
}
