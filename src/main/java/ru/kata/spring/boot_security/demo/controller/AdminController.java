package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserResponseDto;
import ru.kata.spring.boot_security.demo.exception_handling.UserNotFoundException;
import ru.kata.spring.boot_security.demo.mapper.UserMapper;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private static final String MESSAGE_USER_NOT_FOUND = "There is no user with ID = %s int database";
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/current_user")
    public UserResponseDto getCurrentUser(Principal principal) {
        return UserMapper.toDto(userService.findByUsername(principal.getName()));
    }

//    @GetMapping
//    public Iterable<User> getAllUsers() {
//        return userService.findAll();
//    }
    @GetMapping("/users")
    public Collection<UserResponseDto> getAllUsers() {
        return StreamSupport.stream(userService.findAll().spliterator(), false)
                .map(UserMapper::toDto)
                .collect(Collectors.<UserResponseDto>toList());
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
//        Optional<User> user = Optional.ofNullable(userService.findById(id));
//        if (user.isEmpty()) {
//            throw new UserNotFoundException(String.format(MESSAGE_USER_NOT_FOUND, id));
//        }
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