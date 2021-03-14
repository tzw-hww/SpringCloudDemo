package com.example.consumer;

import com.tzw.user.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "provider")
public interface AppInter extends UserApi {

    @RequestMapping("/helloOne")
    public String getHelloOne();

    @RequestMapping(value="/helloTwo")
    public String getHelloTwo(@RequestParam String name);

    @RequestMapping(value="/helloThree")
    public String getHelloThree(@RequestBody String name);

    @RequestMapping("/user")
    public String getUserbyId(@RequestParam String id);
}
