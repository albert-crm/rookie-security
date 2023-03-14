package com.rookie.bigdata.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author rookie
 * @Description TODO
 * @Date 2023/3/14 22:50
 * @Version 1.0
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    public static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public String hello() {

        logger.debug("authentication: {}", SecurityContextHolder.getContext().getAuthentication());
        return "Hello and congrats, you have successfully accessed inside!";
    }
}