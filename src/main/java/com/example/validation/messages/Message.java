package com.example.validation.messages;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Comparator;

@Getter
@Builder
@ToString
public class Message implements Comparable<Message> {
    private int line;
    private String message;
    private MessageType type;

    @Override
    public int compareTo(Message other) {
        return Integer.compare(this.getLine(), other.getLine());
    }
}
