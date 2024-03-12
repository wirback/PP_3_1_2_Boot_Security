package ru.kata.spring.boot_security.demo.controller;

// TODO controller admin class for CRUD
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.RoleService;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//import java.security.Principal;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//    private final UserService userService;
//    private final RoleService roleService;
//    private final PasswordEncoder passwordEncoder;
//
//
//    @Autowired
//    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
//        this.userService = userService;
//        this.roleService = roleService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//
//    @GetMapping()
//    public String getAllUsers(@ModelAttribute("user") User user, Model model, Principal principal) {
//        model.addAttribute("users", userService.findAll());
//        model.addAttribute("roles", roleService.findAll());
//        model.addAttribute("authUser", userService.findByUsername(principal.getName()));
//
//        return "admin/adminPanel";
//    }
//
//    @PostMapping()
//    public String create(@ModelAttribute("user") User user) {
//        userService.save(user);
//
//        return "redirect:/admin";
//    }
//
//    @PostMapping("/update")
//    public String update(@ModelAttribute("user") User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userService.save(user);
//
//        return "redirect:/admin";
//    }
//
//    @PostMapping("/delete")
//    public String delete(@RequestParam("id") Long id) {
//        userService.deleteById(id);
//
//        return "redirect:/admin";
//    }
//}