package com.example.consumer;

import com.example.consumer.AppInter;
import org.springframework.stereotype.Component;

@Component
public class FallbackService implements AppInter {
    @Override
    public String getHelloOne() {
        return "我是helloOne调用的降级";
    }

    @Override
    public String getHelloTwo(String name) {
        return null;
    }

    @Override
    public String getHelloThree(String name) {
        return null;
    }

    @Override
    public String getUserbyId(String id) {
        return null;
    }

    @Override
    public String alive() {
        return null;
    }
}
