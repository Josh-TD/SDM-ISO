package com.example.sdmisoback.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/")
    public String index() {
        return "Hello World - Love From Local Host 8080 :)";
    }
}