package com.example.validation.messages;

import com.example.validation.messages.severity.ErrorGroup;
import com.example.validation.messages.severity.SeverityGroup;
import com.example.validation.messages.severity.WarningGroup;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
public enum MessageType {

    WARNING(WarningGroup.class),
    ERROR(ErrorGroup.class);

    private final Class<? extends SeverityGroup> severity;

    public static Optional<MessageType> getBySeverity(Class<? extends SeverityGroup> severity) {
        return Stream.of(values())
                .filter(value -> value.severity.equals(severity))
                .findFirst();
    }
}
