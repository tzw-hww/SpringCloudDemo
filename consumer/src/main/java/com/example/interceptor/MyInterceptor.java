package com.example.interceptor;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * 拦截器
 */
public class MyInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        System.out.println("01请求进入拦截......");
        System.out.println("进入查看头信息："+httpRequest.getHeaders());
        System.out.println("02 开始执行请求---");
        ClientHttpResponse clientHttpResponse = clientHttpRequestExecution.execute(httpRequest, bytes);
        System.out.println("03 返回响应---");

        System.out.println("响应查看头信息："+clientHttpResponse.getHeaders());

        return clientHttpResponse;
    }
}
