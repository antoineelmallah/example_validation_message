package com.example.validation.validation.custom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class FabricationYearValidator implements ConstraintValidator<FabricationYear, Integer> {
    int inferiorLimit;
    @Override
    public void initialize(FabricationYear constraintAnnotation) {
        this.inferiorLimit = constraintAnnotation.inferiorLimit();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null)
            return true;
        if (value < inferiorLimit)
            return false;
        return value <= LocalDate.now().getYear();
    }
}
