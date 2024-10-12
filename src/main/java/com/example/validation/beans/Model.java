package com.example.validation.beans;

import com.example.validation.messages.severity.ErrorGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Model {
    @Size(max = 20, message = "Description size should have until 20 chars.", groups = ErrorGroup.class)
    @NotNull(message = "Brand description should not be null.", groups = ErrorGroup.class)
    private String description;
}
