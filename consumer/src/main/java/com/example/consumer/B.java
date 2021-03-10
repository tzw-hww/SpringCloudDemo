package com.example.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class B {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping("/h")
    public String getH(){

        ServiceInstance provider = loadBalancerClient.choose("provider");
        String url = "http://" + provider.getHost() + ":" + provider.getPort() + "/hello";
        System.out.println(url);
        String forObject = restTemplate.getForObject(url, String.class);
        return "调用的端口666666："+provider.getPort()+":"+forObject;


    }


    @RequestMapping("/I")
    public String getI(){

        List<ServiceInstance> serviceInstance = discoveryClient.getInstances("provider");
        //随机
//        int i = new Random().nextInt(5);
//        int index = i % serviceInstance.size();
//        System.out.println(i+":"+index+":"+serviceInstance.size());
//        ServiceInstance provider = serviceInstance.get(index);

        //轮询
        AtomicInteger atomicInteger = new AtomicInteger(1);
        int andIncrement = atomicInteger.getAndIncrement();
        int index = andIncrement % serviceInstance.size();
        System.out.println(andIncrement+":"+index);
        ServiceInstance provider = serviceInstance.get(index);

        String url = "http://" + provider.getHost() + ":" + provider.getPort() + "/hello";
        System.out.println(url);
        String forObject = restTemplate.getForObject(url, String.class);
        return "调用的端口为："+provider.getPort()+":"+forObject;


    }

    @RequestMapping("/J")
    public String getJ(){

        ServiceInstance provider = loadBalancerClient.choose("provider");
        String url = "http://" + provider.getHost() + ":" + provider.getPort() + "/hello";
        System.out.println(url);
        String forObject = restTemplate.getForObject(url, String.class);
        return "调用的端口为："+provider.getPort()+":"+forObject;


    }
}
