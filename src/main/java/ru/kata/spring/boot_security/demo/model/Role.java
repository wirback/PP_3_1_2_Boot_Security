package ru.kata.spring.boot_security.demo.model;

//import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@Table(name = "t_role")
//public class Role implements GrantedAuthority {
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    @Transient
//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    public Role() {
    }

//    public Role(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }


    public void addUserToRole(User user) {
        if (users == null) {
            users = new HashSet<>();
        }
        users.add(user);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
    }

//    @Override
//    public String getAuthority() {
//        return getUsername();
//    }
}
