package com.example.consumer;

import com.example.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * RestTemplate API
 */
@RestController
public class C {

    @Autowired
    RestTemplate restTemplate;


    @RequestMapping("/K")
    public String getK(){

        String url = "http://provider/hello";
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }

    //参数为Spring，返回为spring
    @RequestMapping("/L")
    public String getL(){
        String url = "http://provider/hello2?name={1}";
        String forObject = restTemplate.getForObject(url,String.class,"zhangsan");
        return forObject;
    }

    //参数为Map，返回为spring
    @RequestMapping("/M")
    public String getM(){

        String url = "http://provider/hello3?name={name}";
        Map<String, String> stringStringMap = Collections.singletonMap("name", "lisi");
        String forObject = restTemplate.getForObject(url, String.class, stringStringMap);
        return forObject;
    }

    //参数为map,返回为map
    @RequestMapping("/O")
    public String getO(){

        String url = "http://provider/hello4?name={name}";
        Map<String, String> stringStringMap = Collections.singletonMap("name", "lisi");
        Map<String,Object> forObject = restTemplate.getForObject(url, Map.class, stringStringMap);
        return forObject.toString();
    }



    //Post请求，参数为对象，返回为String
    @GetMapping("/N")
    public String save(){
        System.out.println("------------------");
        String url = "http://provider/hello5";
        Map<String, String> stringStringMap = Collections.singletonMap("name", "lisi");
        Person person = new Person("东方不败",22);
        String s = restTemplate.postForObject(url, person, String.class);
        System.out.println("------------------");
        return s;
    }

}
