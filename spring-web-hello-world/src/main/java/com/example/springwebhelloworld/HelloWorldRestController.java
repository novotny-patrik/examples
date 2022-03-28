package com.example.springwebhelloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

    @GetMapping("hello-world")
    String helloWorld() {
        return "Hello world!";
    }

}
