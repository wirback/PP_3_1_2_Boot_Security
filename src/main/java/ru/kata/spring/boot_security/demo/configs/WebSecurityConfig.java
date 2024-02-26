//package ru.kata.spring.boot_security.demo.configs;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//
//import javax.sql.DataSource;
//
////@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
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
////    @Bean
////    @Override
////    public UserDetailsService userDetailsService() {
//////        UserDetails user = User.withDefaultPasswordEncoder()
////        UserDetails user = User.builder()
////                .username("user")
////                .password("{bcrypt}$2a$12$LbltxL6UlkO9jIuz8FrP1.Sba0C4awN1TENNE6OprKSE03rAj.E3q")
////                .roles("USER")
////                .build();
////
////        UserDetails admin = User.builder()
////                .username("admin")
////                .password("{bcrypt}$2a$12$LbltxL6UlkO9jIuz8FrP1.Sba0C4awN1TENNE6OprKSE03rAj.E3q")
////                .roles("ADMIN", "USER")
////                .build();
////
////        return new InMemoryUserDetailsManager(user, admin);
////    }
//
//    // аутентификация jdbc
////    @Bean
////    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
////        UserDetails user = User.builder()
////                .username("user")
////                .password("{bcrypt}$2a$12$LbltxL6UlkO9jIuz8FrP1.Sba0C4awN1TENNE6OprKSE03rAj.E3q")
////                .roles("USER")
////                .build();
////
////        UserDetails admin = User.builder()
////                .username("admin")
////                .password("{bcrypt}$2a$12$LbltxL6UlkO9jIuz8FrP1.Sba0C4awN1TENNE6OprKSE03rAj.E3q")
////                .roles("ADMIN", "USER")
////                .build();
////
////        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
////        userDetailsManager.createUser(user);
////        userDetailsManager.createUser(admin);
////
////        return userDetailsManager;
////    }
//}