package ru.kata.spring.boot_security.demo.repositories;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
//    User findByUsername(String username);
}
