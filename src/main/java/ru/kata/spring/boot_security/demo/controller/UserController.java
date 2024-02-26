package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());

        return "users/usersList";
    }

    @GetMapping("/show")
    public String show(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "users/showUser";
    }
}
