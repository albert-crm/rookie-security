//package com.rookie.bigdata.service;
//
//import org.apache.catalina.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * @Classname UserDetailsServiceImplTest
// * @Description TODO
// * @Author rookie
// * @Date 2023/3/4 22:45
// * @Version 1.0
// */
//@SpringBootTest
////@ActiveProfiles("uat")
//class UserDetailsServiceImplTest {
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
////    @Autowired
////    private AuthenticationManager authenticationManager;
//
//    @Test
//    void loadUserByUsername() {
//
//        System.out.println(passwordEncoder.encode("user"));
//
//        boolean root = passwordEncoder.matches("user", "$2a$10$VMma9UIZqjEOdSxRYIThTOldFTlc50E4ECPRhvyhREmtl6YNutAmW");
//
////        System.out.println(authenticationManager);
//        System.out.println(root);
//    }
//}