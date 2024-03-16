package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping()
//    public ResponseEntity<User> show(@AuthenticationPrincipal User user) {
//        return ResponseEntity.ok(user);
//    }
//    @GetMapping("/show")
//    public ResponseEntity<User> show(Principal principal) {
//        return new ResponseEntity<>(userService.findByUsername(principal.getName()), HttpStatus.OK);
//    }

    @GetMapping("/show")
    public User show(Principal principal) {
        return userService.findByUsername(principal.getName());
    }
}
