package com.sarayeva.cybercubebetask.exception;

import static com.sarayeva.cybercubebetask.constant.Constants.INSUFFICIENT_BUDGET_ERROR_MESSAGE;

public class InsufficientBudgetException extends RuntimeException {

  public InsufficientBudgetException() {
    super(INSUFFICIENT_BUDGET_ERROR_MESSAGE);
  }
}
