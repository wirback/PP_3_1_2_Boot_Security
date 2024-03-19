package ru.kata.spring.boot_security.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class UserCreationDto {
    @JsonProperty("FIRST_NAME")
    private final String firstName;
    @JsonProperty("LAST_NAME")
    private final String lastName;
    @JsonProperty("AGE")
    private final Integer age;
    @JsonProperty("USERNAME")
    private final String username;
    @JsonProperty("PASSWORD")
    private final String password;
    @JsonProperty("ROLES")
    private final Collection<RoleDto> roles;

    public UserCreationDto() {
        this(null, null, null, null, null, null);
    }

    public UserCreationDto(String firstName, String lastName, Integer age, String username, String password, Collection<RoleDto> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.password = password;
        this.roles = roles;
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

    public String getPassword() {
        return password;
    }

    public Collection<RoleDto> getRoles() {
        return roles;
    }
}
