package com.example.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController{

    @Autowired
    AppInter appInter;

    @Value("${server.port}")
    private String port;

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
        return "consumer:"+port+"---------->"+appInter.getUserbyId("1");
    }


    /**
     *
     *     try{
     *         服务降级
     *         1. 发起请求到服务方，
     *              1.1 判断是否超时，如果超时了,进入1.2
     *              1.2 判断其他的方法器是否超时，如果也超时了，进入catch，备用方案
     *              1.3 调用服务出现异常
     *         服务隔离
     *
     *         某个具体的服务有大量的访问时，每个请求都会开启一个线程，
     *         map(请求uri,线程数量)，在线程池中进行操作。所以相同的请求，限定一个阈值（阀值），将每个具体的服务接口隔离，每个不同的请求都已自己的线程池。
     *
     *         if(阈值){
     *             throw new Excetpion()
     *         }
     *
     *         服务熔断
     *         每次请求失败，都会记一个失败数，count++，当失败次数达到一定的值是，直接熔断
     *         以后发过来的请求都会直接走throw
     *         if(count++>10){
     *             throw new Exception()
     *         }
     *
     *         服务熔断，请求的状态，开/关/半开
     *          开，请求
     *          关，不请求，
     *          半开，隔一段时间发一次试一试，如果发现请求成功看，那么就开请求。同时重置一下count。
     *
     *          如果有服务消费者有多个，相互之间不需要通知，一个调不通，并不代表另一个也调不通。
     *
     *          Hystrix并不是用Try。catch实现的。通过代理的方式实现。
     *
     *     }catch(Exception e){
     *
     *        备用方案：
     *        1. 返回一个友好的提示，页面等
     *        2. 返回一个其他的备用方案。
     *     }
     *
     *
     *
     *
     */



}
