package com.example.validation.beans;

import com.example.validation.messages.severity.ErrorGroup;
import com.example.validation.messages.severity.WarningGroup;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Builder
public class Person {
    @CPF(message = "CPF invalid.", groups = ErrorGroup.class)
    @NotNull(message = "CPF should be provided.", groups = ErrorGroup.class)
    private String cpf;
    @NotNull(message = "Name should be provided.", groups = ErrorGroup.class)
    @Size(min = 10, max = 50, message = "Name size should have size between {min} and {max} characters.", groups = WarningGroup.class)
    private String name;
    @NotNull(message = "Birth date should be provided.", groups = {ErrorGroup.class, WarningGroup.class})
    private LocalDate birthdayDate;

    @AssertTrue(message = "Person should have age between 16 and 80 years.", groups = ErrorGroup.class)
    public boolean isValidBirthdate() {
        if (birthdayDate == null)
            return true;
        var currentDate = LocalDate.now();
        var age = ChronoUnit.YEARS.between(birthdayDate, currentDate);
        if (age < 16)
            return false;
        return age <= 80;
    }
}
