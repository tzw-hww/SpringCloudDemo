package com.example.consumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "back")
    public String getUser(){
        String url = "http://provider/user";
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }

    public String back(){
        return "RestTemplate降级处理";
    }
}
