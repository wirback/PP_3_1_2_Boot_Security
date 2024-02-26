package ru.kata.spring.boot_security.demo.model;

//import org.springframework.security.core.GrantedAuthority;

//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
////@Table(name = "t_role")
////public class Role implements GrantedAuthority {
//public class Role {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String role;
////    @Transient
////    @ManyToMany(mappedBy = "roles")
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "user_role",
//            joinColumns = @JoinColumn(name = "role_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private Set<User> users;
//
//    public Role() {
//    }
//
//    public Role(Long id, String name) {
//        this.id = id;
//        this.role = name;
//    }
//
//    public Long getId() {
//        return id;
//    }
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return role;
//    }
//    public void setUsername(String name) {
//        this.role = name;
//    }
//
//    public Set<User> getUsers() {
//        return users;
//    }
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }

//    @Override
//    public String getAuthority() {
//        return getUsername();
//    }
//}
