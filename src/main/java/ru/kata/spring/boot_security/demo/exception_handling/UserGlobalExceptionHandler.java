package ru.kata.spring.boot_security.demo.exception_handling;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserNotFoundDataJSON> handleException(UserNotFoundException e) {
        UserNotFoundDataJSON dataJSON = new UserNotFoundDataJSON();
        dataJSON.setMessage(e.getMessage());
        return new ResponseEntity<>(dataJSON, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<UserNotFoundDataJSON> handleException(Exception e) {
        UserNotFoundDataJSON dataJSON = new UserNotFoundDataJSON();
        dataJSON.setMessage(e.getMessage());
        return new ResponseEntity<>(dataJSON, HttpStatus.BAD_REQUEST);
    }
}
