package com.example.consumer.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "nacos-provider-1")
public interface ProviderClientOne {

    @GetMapping("/getTest1")
    String getTest1();
}

//javaagent:/Users/bn/GoProject/src/code.byted.org/epscp/java-agent/target/application-premain-jar-with-dependencies.jar
//       -javaagent:/Users/bn/GoProject/src/code.byted.org/epscp/java-agent/jmx_prometheus_javaagent-0.16.1.jar=5555:/Users/bn/GoProject/src/code.byted.org/epscp/java-agent/prometheus-jmx-config.yaml