package com.example.userapi;

import org.springframework.web.bind.annotation.RequestMapping;

//@RequestMapping("user")
public interface UserApi {

    @RequestMapping("alive")
    public String alive();
}
