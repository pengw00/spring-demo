package com.example.sessiondemo.exception;

import java.util.Collections;
import java.util.List;

import lombok.Getter;

@Getter
public class UserCreationException extends RuntimeException {
    private List<String> messages;

    public UserCreationException(String message) {
        super(message);
        this.messages = Collections.singletonList(message);
    }

    public UserCreationException(List<String> messages) {
        this.messages = messages;
    }

}
