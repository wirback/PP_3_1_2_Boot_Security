package ru.kata.spring.boot_security.demo.service;

//import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

//@Service
public interface UserService {
    Iterable<User> findAll();
    User findById(Long id);
    void save(User user);
    void deleteById(Long id);
}
