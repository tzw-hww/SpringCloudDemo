package com.example.provider;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class Helloo {
    public String getHello(){
        return "Hi";
    }
}
