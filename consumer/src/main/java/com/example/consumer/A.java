package com.example.consumer;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class A {

    @Autowired
    DiscoveryClient discoveryClient;
    @Qualifier("eurekaClient")
    @Autowired
    EurekaClient eurekaClient;
    @Autowired
    EurekaDiscoveryClient eurekaDiscoveryClient;
    /**
     * 负载均衡
     */
    @Autowired
    LoadBalancerClient balancerClient;


    @RequestMapping("/a")
    public String getA() {
        return "a";

    }

    @RequestMapping("/b")
    public String getB() {

        List<String> services = discoveryClient.getServices();
        for (String str : services) {
            System.out.println(str);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("provider");
        for (ServiceInstance instance : instances) {
            System.out.println(ToStringBuilder.reflectionToString(instance));
        }
        return "b";


    }

    @RequestMapping("/c")
    public String getC() {
        System.out.println("进入c");
        List<InstanceInfo> instances = eurekaClient.getInstancesByVipAddress("provider", false);
        for (InstanceInfo instance : instances) {
            System.out.println(ToStringBuilder.reflectionToString(instance));
        }
        if (instances.size() > 0) {
            InstanceInfo instance = instances.get(0);
            if (InstanceInfo.InstanceStatus.UP.equals(instance.getStatus())) {
                System.out.println(instance.getStatus());
                System.out.println(instance.getInstanceId());
                System.out.println(instance.getAppName());
                System.out.println(instance.getHostName());
                System.out.println(instance.getIPAddr());
                System.out.println(instance.getPort());
                String url = "http://" + instance.getHostName() + ":" + instance.getPort() + "/hello";
                System.out.println(url);

                RestTemplate restTemplate = new RestTemplate();
                String forObject = restTemplate.getForObject(url, String.class);
                System.out.println(forObject);
            }
        }

        return "c";
    }

    @RequestMapping("/d")
    private String getD() {

        List<ServiceInstance> provider = eurekaDiscoveryClient.getInstances("provider");
        for (ServiceInstance serviceInstance : provider) {
            System.out.println(ToStringBuilder.reflectionToString(serviceInstance));
            System.out.println(serviceInstance.getInstanceId());
            System.out.println(serviceInstance.getPort());
            System.out.println(serviceInstance.getHost());
            System.out.println(serviceInstance.getServiceId());
            System.out.println(serviceInstance.getMetadata());
            System.out.println(serviceInstance.getScheme());
            System.out.println(serviceInstance.getUri());
            System.out.println(serviceInstance.isSecure());
            InstanceInfo instanceInfo = (InstanceInfo) serviceInstance;
            System.out.println(instanceInfo.getStatus());

        }
        return "dd";
    }

    /**
     * 负载均衡，在choose中自己找一个，同时过滤掉down的服务
     */
    @RequestMapping("/e")
    public String getE() {

        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance provider = balancerClient.choose("provider");
        String url = "http://" + provider.getHost() + ":" + provider.getPort() + "/hello";
        System.out.println(url);
        String forObject = restTemplate.getForObject(url, String.class);
        System.out.println(forObject);

        return "e";
    }

    /**
     * 负载均衡，在choose中自己找一个，同时过滤掉down的服务
     */
    @RequestMapping("/f")
    public String getF() {
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance provider = balancerClient.choose("provider");
        String url = "http://" + provider.getHost() + ":" + provider.getPort() + "/hello";
        System.out.println(url);
        String forObject = restTemplate.getForObject(url, String.class);
        System.out.println(forObject);

        return "f";
    }





}
