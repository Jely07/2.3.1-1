package com.jely91.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    @GetMapping("/hello-world")
    public String sayHello(){
        return "hello_world";
    }
}
