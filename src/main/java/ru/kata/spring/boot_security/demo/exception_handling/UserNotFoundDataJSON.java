package ru.kata.spring.boot_security.demo.exception_handling;

public class UserNotFoundDataJSON {
    private String message;

    public UserNotFoundDataJSON() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
