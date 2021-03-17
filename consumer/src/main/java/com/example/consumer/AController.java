package com.example.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AController {

    @Autowired
    UserService userService;

    @RequestMapping("/user")
    public String getUser(){
        String user = userService.getUser();
        return user;
    }
}
