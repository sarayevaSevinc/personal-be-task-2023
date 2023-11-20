package com.sarayeva.personalbetask.exception;

import static com.sarayeva.personalbetask.constant.Constants.INSUFFICIENT_BUDGET_ERROR_MESSAGE;

public class InsufficientBudgetException extends RuntimeException {

  public InsufficientBudgetException() {
    super(INSUFFICIENT_BUDGET_ERROR_MESSAGE);
  }
}
