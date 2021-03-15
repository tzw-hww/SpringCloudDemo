package com.example.provider;

import com.example.userapi.UserApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController implements UserApi {

    @Value("${server.port}")
    private String port;

    public int a=0;

    @RequestMapping("/helloOne")
    public String getHelloOne(){

        return port+":"+"helloOne";
    }

    @GetMapping("/helloTwo")
    public String getHelloTwo(@RequestParam  String name){

        return port+":"+name;
    }

    @PostMapping("/helloThree")
    public String getHelloThree(@RequestBody  String name){

        return port+":"+name;
    }


    @Override
    public String alive() {
        return "I'm alive";
    }

    @RequestMapping("/user")
    public String getUserbyId(@RequestParam String id){

        System.out.println(port+":进入服务。。。。。。。。"+a++);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        return "用户名称：张三-----"+"用户id:"+id;

    }
}
