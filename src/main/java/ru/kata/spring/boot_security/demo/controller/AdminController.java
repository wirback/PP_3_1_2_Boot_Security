package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;


    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());

        return "admin/adminPanel";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "admin/editUser";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "admin/newUser";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}