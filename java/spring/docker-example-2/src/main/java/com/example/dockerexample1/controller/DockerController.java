package com.example.dockerexample1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerController {

    @GetMapping("/hello-docker")
    public String helloDocker() {
        return "helloDocker";
    }
}
