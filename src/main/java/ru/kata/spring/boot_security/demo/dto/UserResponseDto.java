package ru.kata.spring.boot_security.demo.dto;

import java.util.Collection;


public class UserResponseDto {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final Integer age;
    private final String username;
    private final Collection<RoleDto> roles;

//    public UserResponseDto() {
//        this(null, null, null, null, null, null);
//    }

    public UserResponseDto(Long id, String firstName, String lastName, Integer age, String username,
                           Collection<RoleDto> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.roles = roles;
    }


    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    public Collection<RoleDto> getRoles() {
        return roles;
    }
}
