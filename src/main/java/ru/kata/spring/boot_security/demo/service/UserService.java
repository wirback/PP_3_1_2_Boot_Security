package ru.kata.spring.boot_security.demo.service;

//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

//@Service
public interface UserService extends UserDetailsService {
    Iterable<User> findAll();
    User findById(Long id);
    void save(User user);
    void deleteById(Long id);
}
