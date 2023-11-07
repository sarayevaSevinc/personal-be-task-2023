package com.sarayeva.cybercubebetask.validation;

import com.sarayeva.cybercubebetask.constant.AnalysisType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnalysisTypeValidator implements ConstraintValidator<ValidAnalysisType, AnalysisType> {

    private List<String> acceptedValues;

    @Override
    public void initialize(ValidAnalysisType constraintAnnotation) {
        acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(AnalysisType value, ConstraintValidatorContext context) {
        return acceptedValues.contains(value.toString());
    }
}
