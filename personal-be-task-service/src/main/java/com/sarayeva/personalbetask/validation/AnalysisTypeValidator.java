package com.sarayeva.personalbetask.validation;

import com.sarayeva.personalbetask.exception.AnalysisTypeValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnalysisTypeValidator implements ConstraintValidator<ValidAnalysisType, String> {

  private List<String> acceptedValues;

  @Override
  public void initialize(ValidAnalysisType constraintAnnotation) {
    acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
        .map(Enum::name)
        .collect(Collectors.toList());
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (!acceptedValues.contains(value)) {
      throw new AnalysisTypeValidationException();
    }
    return true;
  }
}
