package com.example.validation.beans;

import com.example.validation.validation.custom.FabricationYear;
import com.example.validation.messages.severity.ErrorGroup;
import com.example.validation.messages.severity.WarningGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Car implements LineBasedBean {
    @NotNull(message = "Fabrication year should be provided.", groups = ErrorGroup.class)
    @FabricationYear(message = "Invalid fabrication year.", groups = ErrorGroup.class)
    private Integer fabricationYear;
    @Valid
    @NotNull(message = "Model should not be null.", groups = WarningGroup.class)
    private Model model;
    @Valid
    @NotNull(message = "Brand should not be null.", groups = WarningGroup.class)
    private Brand brand;
    @Size(min = 1, message = "A car should have at least one owner.", groups = WarningGroup.class)
    private List<@Valid Person> owners;
    @Size(min = 1, message = "A car should have at least one driver.", groups = WarningGroup.class)
    private List<@Valid Person> drivers;
    private int line;
}
