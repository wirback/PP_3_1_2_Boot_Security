package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;


public interface RoleService {
    Iterable<Role> findAll();
    Role findById(Long id);
    void save(Role role);
    void deleteById(Long id);
}
