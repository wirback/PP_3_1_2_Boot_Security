package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.RoleDto;
import ru.kata.spring.boot_security.demo.dto.UserResponseDto;
import ru.kata.spring.boot_security.demo.dto.UserSaveDto;
import ru.kata.spring.boot_security.demo.exception_handling.UserNotFoundException;
import ru.kata.spring.boot_security.demo.mapper.UserMapper;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
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
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/user")
    public UserResponseDto getCurrentUser(Principal principal) {
        return UserMapper.toDto(userService.findByUsername(principal.getName()));
    }

    @GetMapping("/users")
    public ResponseEntity<Collection<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(StreamSupport.stream(userService.findAll().spliterator(), false)
                .map(UserMapper::toDto)
                .collect(Collectors.<UserResponseDto>toList()));
    }

    @GetMapping("/roles")
    public Collection<RoleDto> getRoles() {
        return StreamSupport.stream(roleService.findAll().spliterator(), false)
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(UserMapper.toDto(userService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addNewUser(@RequestBody UserSaveDto userSaveDto) {
        userService.save(UserMapper.toEntity(userSaveDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserSaveDto userSaveDto) {
        User user = UserMapper.toEntity(userSaveDto);
        if (Optional.ofNullable(userService.findById(user.getId())).isEmpty()) {
            throw new  UserNotFoundException(String.format(MESSAGE_USER_NOT_FOUND, user.getId()));
        }
        userService.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        if (Optional.ofNullable(userService.findById(id)).isEmpty()) {
            throw new UserNotFoundException(String.format(MESSAGE_USER_NOT_FOUND, id));
        }
        userService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}