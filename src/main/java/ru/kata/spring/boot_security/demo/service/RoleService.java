package ru.kata.spring.boot_security.demo.service;

//import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

//@Service
public interface RoleService {
    Iterable<Role> findAll();
    Role findById(Long id);
    void save(Role role);
    void deleteById(Long id);
}
