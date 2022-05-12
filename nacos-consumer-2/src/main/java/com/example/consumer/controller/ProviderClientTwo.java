package com.example.consumer.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "nacos-consumer-1")
public interface ProviderClientTwo {

    //, headers = {"Content-Type=application/json;charset=UTF-8", "App-Secret=test"}
    @GetMapping("/test1")
    String test1();
}