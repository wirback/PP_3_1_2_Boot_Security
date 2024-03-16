package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.exception_handling.UserNotFoundDataJSON;
import ru.kata.spring.boot_security.demo.exception_handling.UserNotFoundException;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Optional;


@RestController
@RequestMapping("/admin")
public class AdminController {
    private static final String MESSAGE_USER_NOT_FOUND = "There is no user with ID = %s int database";
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        Optional<User> user = Optional.ofNullable(userService.findById(id));
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format(MESSAGE_USER_NOT_FOUND, id));
        }
        return userService.findById(id);
    }

    @PostMapping
    public User addNewUser(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        Optional<User> tmpUser = Optional.ofNullable(userService.findById(user.getId()));
        if (tmpUser.isEmpty()) {
            throw new  UserNotFoundException(String.format(MESSAGE_USER_NOT_FOUND, user.getId()));
        }
        userService.save(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        Optional<User> user = Optional.ofNullable(userService.findById(id));
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format(MESSAGE_USER_NOT_FOUND, id));
        }
        userService.deleteById(id);
        return String.format("User with ID = %s was deleted.", id);
    }
}