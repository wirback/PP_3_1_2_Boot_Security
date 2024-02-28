package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
//public class UserServiceImpl implements UserService, UserDetailsService {
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        Optional<Role> foundRole = roleRepository.findById(id);
        return foundRole.orElse(null);
    }

    @Override
    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

//    public User findByUsername(String email) {
//        return userRepository.findByUsername(email);
//    }
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
////        User user = findByUsername(email);
////        if (user == null) {
////            throw new UsernameNotFoundException(String.format("User '%s' not found", email));
////        }
////        return new org.springframework.security.core.userdetails
////                .User(user.getUsername(), user.getPassword(), mapRoleToAuthorities(user.getRoles()));
//
//    }

//    private Collection<? extends GrantedAuthority> mapRoleToAuthorities(Collection<Role> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getUsername())).collect(Collectors.toList());
//    }
}
