package com.rookie.bigdata.config;

import com.rookie.bigdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * @Author rookie
 * @Description TODO
 * @Date 2023/3/14 22:35
 * @Version 1.0
 */
@Configuration
public class SecurityConfiguration {

    @Autowired
    private UserService userService;




    @Bean
    public UserDetailsManager userDetailsManager(){


//        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();

        userService.createUser(user);


        return new UserDetailsManager() {
            @Override
            public void createUser(UserDetails user) {
                userService.createUser(user);
            }

            @Override
            public void updateUser(UserDetails user) {
                userService.updateUser(user);
            }

            @Override
            public void deleteUser(String username) {
                userService.deleteUser(username);
            }

            @Override
            public void changePassword(String oldPassword, String newPassword) {
                userService.changePassword(oldPassword, newPassword);
            }

            @Override
            public boolean userExists(String username) {
                return userService.userExists(username);
            }

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userService.loadUserByUsername(username);
            }
        };
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    public UserDetailsService users() {
//        // The builder will ensure the passwords are encoded before saving in memory
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        UserDetails user = users
//                .username("user")
//                .password(passwordEncoder.encode("password"))
//                .roles("USER")
//                .build();
//        UserDetails admin = users
//                .username("admin")
//                .password(passwordEncoder.encode("password"))
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }

//    @Bean
//    public UserDetailsService users() {
//        // The builder will ensure the passwords are encoded before saving in memory
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        UserDetails user = users
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
////        System.out.println(passwordEncoder().encode("password"));
//        UserDetails admin = users
//                .username("admin")
//                .password("password")
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }



}
