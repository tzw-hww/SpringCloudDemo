package com.example.consumer;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class A {

    @Autowired
    DiscoveryClient discoveryClient;
    @Qualifier("eurekaClient")
    @Autowired
    EurekaClient eurekaClient;

    @RequestMapping("/a")
    public String getA(){
        return "a";

    }
    @RequestMapping("/b")
    public String getB(){

        List<String> services = discoveryClient.getServices();
        for (String str:services) {
            System.out.println(str);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("provider");
        for (ServiceInstance instance : instances) {
            System.out.println(ToStringBuilder.reflectionToString(instance));
        }
        return "b";


    }

    @RequestMapping("/c")
    public String getC(){


        List<InstanceInfo> instances = eurekaClient.getInstancesById("provider");

        for (InstanceInfo instance : instances) {

        }


        return "c";


    }

}
