package com.example.consumer.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "eureka-provider-1")
public interface ProviderClientOne {
    @GetMapping("/getTest1")
    String getTest1();
}