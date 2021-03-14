package com.example.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController{

    @Autowired
    AppInter appInter;

//    @Autowired
//    MyApi myApi;

    @RequestMapping("/helloOne")
    public String HelloOne(){
        return appInter.getHelloOne();
    }

    @RequestMapping("/helloTwo")
    public String HelloTwo(){
        return appInter.getHelloTwo("helloTwo");
    }

    @RequestMapping("/helloThree")
    public String HelloThree(){
        return appInter.getHelloThree("helloThree");
    }

    @RequestMapping("/alive")
    public String alive(){
        return appInter.alive();
    }

    @RequestMapping("/helloFour")
    public String helloFour(){
        return appInter.getUserbyId("1");
    }


}
