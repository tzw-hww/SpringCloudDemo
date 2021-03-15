package com.example.consumer;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class FallbackFactoryService implements FallbackFactory<AppInter> {
    @Override
    public AppInter create(Throwable throwable) {
        return new AppInter() {
            @Override
            public String getHelloOne() {
                throwable.printStackTrace();
                if(throwable instanceof HttpServerErrorException.InternalServerError) {
                    System.out.println("InternalServerError");
                    return "远程服务报错";
                }else if(throwable instanceof RuntimeException) {

                    return "请求时异常：" + throwable;
                }else {
                    return "这是helloOne接口的降级,FallbackFactory方式";
                }
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
        };
    }
}
