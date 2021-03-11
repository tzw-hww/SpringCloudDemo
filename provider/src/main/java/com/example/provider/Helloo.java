package com.example.provider;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
public class Helloo {

    @Value("${server.port}")
    String port;

    @RequestMapping("/hello")
    public String getHello() {

        return "Hi,port:"+port;
    }

    @RequestMapping("/hello2")
    public String getHello2(@RequestParam String name) {

        return "Hi,port:"+port+"----name:"+name;
    }

    @RequestMapping("/hello3")
    public String getHello3(@RequestParam String name) {

        return "Hi,port:"+port+"----name:"+name;
    }

    @RequestMapping("/hello4")
    public Map<String,String> getHello4(@RequestParam String name) {

        Map<String, String> stringStringMap = Collections.singletonMap("req", "ISCC");
        return stringStringMap;
    }

    @RequestMapping(value = "/hello5",method = RequestMethod.POST)
    public String getHello5(@RequestBody Map name) {
        System.out.println(name);
        return "收到了："+name;
    }

    @PostMapping(value = "/hello6")
    public String getHello6(@RequestBody String name) {
        System.out.println(name);
        return "收到了："+name;
    }

}
