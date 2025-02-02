package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select u from User u left join fetch u.roles where u.username=:username")
    Optional<User> findByUsername(@Param("username") String username);
}
