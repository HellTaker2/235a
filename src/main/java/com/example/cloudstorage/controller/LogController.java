package com.example.cloudstorage.controller;

import com.example.cloudstorage.entity.User;
import com.example.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    @Autowired
    public UserService userservice;

    @GetMapping("/hello")
    public User hello(String uname){
        return userservice.hello(uname);
    }


}
