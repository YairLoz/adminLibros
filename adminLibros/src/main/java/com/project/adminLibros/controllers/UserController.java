package com.project.adminLibros.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.project.adminLibros.models.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("users")
public class UserController {

    @PostMapping("path")
    public User createUser(@RequestBody User user) {
        // TODO: process POST request

        return user;
    }

}
