package com.odpjr.springbootplayground.helloservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greeting")
public class GreetingController {


    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        System.out.println("hello");
        return String.format("Hello %s!", name);
    }

    @GetMapping("/hello")
    public String helloAll() {
        System.out.println("helloall");
        return "Hello everyone!";
    }
}
