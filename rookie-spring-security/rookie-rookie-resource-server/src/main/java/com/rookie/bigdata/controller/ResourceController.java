package com.rookie.bigdata.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname ResourceController
 * @Description 资源controller
 * @Author rookie
 * @Date 2023/3/13 11:08
 * @Version 1.0
 */
@RestController
@RequestMapping("/resource-server")
public class ResourceController {

    @GetMapping("/access")
    public String access() {
        return "authorization-code-resource-server: you're in!";
    }

}