package com.example.sdmisoback.controller;

import com.example.sdmisoback.model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/users")
    public List<User> getUsers() {
        User userOne = new User("userOne@gmail.com", "userOnePass");
        User userTwo = new User("userTwo@gmail.com", "userTwoPass");
        User userThree = new User("userThree@gmail.com", "userThreePass");
        return Arrays.asList(userOne, userTwo, userThree);
    }
}
