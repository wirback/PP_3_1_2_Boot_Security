package ru.kata.spring.boot_security.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class UserSaveDto {
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
    @JsonProperty("PASSWORD")
    private final String password;
    @JsonProperty("ROLES")
    private final Collection<RoleDto> roles;

//    public UserSaveDto() {
//        this(null, null, null, null, null, null);
//    }

    public UserSaveDto(Long id , String firstName, String lastName,
                       Integer age, String username, String password,
                       Collection<RoleDto> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public Collection<RoleDto> getRoles() {
        return roles;
    }
}
