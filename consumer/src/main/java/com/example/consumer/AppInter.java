package com.example.consumer;

import com.example.userapi.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;


@Primary
@FeignClient(name = "provider",fallback = FallbackService.class)
//@FeignClient(name = "provider",fallbackFactory = FallbackFactoryService.class)
public interface AppInter extends UserApi {

    @RequestMapping("/helloOne")
    public String getHelloOne();

    @RequestMapping(value="/helloTwo")
    public String getHelloTwo(@RequestParam String name);

    @RequestMapping(value="/helloThree")
    public String getHelloThree(@RequestBody String name);

    @RequestMapping("/user")
    public String getUserbyId(@RequestParam String id);
}
