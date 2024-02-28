package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import ru.kata.spring.boot_security.demo.security.AuthProviderImpl;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.sql.DataSource;

//@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Autowired
    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }


    // настраивает аунтификацию
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(userService);
        auth.userDetailsService(userService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/login", "/authorization/**").permitAll()
                .anyRequest().authenticated()
                .and()
//                .formLogin().successHandler(successUserHandler)
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }


//    private final SuccessUserHandler successUserHandler;
//
//
//    public WebSecurityConfig(SuccessUserHandler successUserHandler) {
//        this.successUserHandler = successUserHandler;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/index").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().successHandler(successUserHandler)
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }
//
//
//    // аутентификация inMemory
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
////        UserDetails user = User.withDefaultPasswordEncoder()
//        UserDetails user = User.builder()
//                .email("user")
//                .password("{bcrypt}$2a$12$LbltxL6UlkO9jIuz8FrP1.Sba0C4awN1TENNE6OprKSE03rAj.E3q")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .email("admin")
//                .password("{bcrypt}$2a$12$LbltxL6UlkO9jIuz8FrP1.Sba0C4awN1TENNE6OprKSE03rAj.E3q")
//                .roles("ADMIN", "USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    // аутентификация jdbc
//    @Bean
//    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
//        UserDetails user = User.builder()
//                .email("user")
//                .password("{bcrypt}$2a$12$LbltxL6UlkO9jIuz8FrP1.Sba0C4awN1TENNE6OprKSE03rAj.E3q")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .email("admin")
//                .password("{bcrypt}$2a$12$LbltxL6UlkO9jIuz8FrP1.Sba0C4awN1TENNE6OprKSE03rAj.E3q")
//                .roles("ADMIN", "USER")
//                .build();
//
//        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//        userDetailsManager.createUser(user);
//        userDetailsManager.createUser(admin);
//
//        return userDetailsManager;
//    }
}