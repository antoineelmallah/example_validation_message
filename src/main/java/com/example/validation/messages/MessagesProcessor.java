package com.example.validation.messages;

import com.example.validation.beans.LineBasedBean;
import com.example.validation.messages.severity.ErrorGroup;
import com.example.validation.messages.severity.SeverityGroup;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.List;

public class MessagesProcessor {

    private static final Validator validator;

    static {
        try (var factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    public static List<Message> process(List<? extends LineBasedBean> beans, Class<? extends SeverityGroup> severity) {
        return beans.stream()
                .flatMap(bean -> process(bean, severity).stream())
                .sorted()
                .toList();
    }

    public static List<Message> process(LineBasedBean bean, Class<? extends SeverityGroup> severity) {
        int line = bean.getLine();
        if (severity == null)
            severity = ErrorGroup.class;
        var type = MessageType.getBySeverity(severity).orElse(null);
        return validator.validate(bean, severity).stream()
                .map(constraintViolation -> Message.builder()
                        .message(constraintViolation.getMessage())
                        .line(line)
                        .type(type)
                        .build())
                //.peek(System.out::println)
                .toList();
    }
}
