package com.sarayeva.cybercubebetask.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

  public static final String PROFILE_ID_HEADER = "x-profile-id";
  public static final String PROFILE_NOT_FOUND_ERROR_MESSAGE = "PROFILE_NOT_FOUND";
  public static final String VIEWER_NOT_FOUND_ERROR_MESSAGE = "VIEWER_NOT_FOUND";
  public static final String ANALYSIS_NOT_FOUND_ERROR_MESSAGE = "ANALYSIS_NOT_FOUND";
  public static final String INSUFFICIENT_BUDGET_ERROR_MESSAGE = "INSUFFICIENT_BUDGET_FOR_CREATING_ANALYSIS";

}
