package ru.kata.spring.boot_security.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class UserResponseDto {
    @JsonProperty("ID")
    private final Long id;
    @JsonProperty("FIRST_NAME")
    private final String firstName;
    @JsonProperty("LAST_NAME")
    private final String lastName;
    @JsonProperty("AGE")
    private final Integer age;
    @JsonProperty("USERNAME")
    private final String username;
    @JsonProperty("ROLES")
    private final Collection<RoleDto> roles;

    public UserResponseDto() {
        this(null, null, null, null, null, null);
    }

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
