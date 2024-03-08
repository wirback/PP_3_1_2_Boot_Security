package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RegistrationService;
import ru.kata.spring.boot_security.demo.util.UserValidator;


@Controller
@RequestMapping("/authorization")
public class AuthorizationController {
    private final RegistrationService registrationService;
    private final UserValidator userValidator;

    @Autowired
    public AuthorizationController(RegistrationService registrationService, UserValidator userValidator) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
    }


//    @GetMapping("/login")
//    public String loginPage() {
//        return "authorization/login";
//    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user")User user) {
        return "authorization/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "authorization/registration";
        }
        registrationService.register(user);

        return "redirect:/login";
    }
}
