package com.example.validation.validation.custom;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FabricationYearValidator.class)
public @interface FabricationYear {
    int inferiorLimit() default 1950;
    String message() default "Invalid year";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
