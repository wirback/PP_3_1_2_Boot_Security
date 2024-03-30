package ru.kata.spring.boot_security.demo.mapper;

import ru.kata.spring.boot_security.demo.dto.RoleDto;
import ru.kata.spring.boot_security.demo.dto.UserSaveDto;
import ru.kata.spring.boot_security.demo.dto.UserResponseDto;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.stream.Collectors;


public class UserMapper {

    public static UserResponseDto toDto(User entity) {
        return new UserResponseDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getAge(),
                entity.getUsername(),
                entity.getRoles().stream()
                        .map(UserMapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    public static User toEntity(UserSaveDto dto) {
        return new User(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAge(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getRoles().stream()
                        .map(UserMapper::toEntity)
                        .collect(Collectors.toList())
        );
    }

    public static RoleDto toDto(Role entity) {
        return new RoleDto(entity.getId(), entity.getName());
    }

    public static Role toEntity(RoleDto dto) {
        return new Role(dto.getId(), dto.getName());
    }
}
