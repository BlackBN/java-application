package com.example.consumer.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class TestController {
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private Integer serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    @Qualifier("restTemplateCustom") // 表示根据名称来找bean
    private RestTemplate restTemplateCustom;

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private ProviderClientOne providerClientOne;

    @Autowired
    private ProviderClientTwo providerClientTwo;

    @RequestMapping("/test1")
    public String test1() {
        return applicationName + ":" + serverPort + " " + providerClientOne.getTest1();
    }

    @RequestMapping("/test2")
    public String test2() {
        return applicationName + ":" + serverPort + " " + providerClientTwo.test1();
    }

    @RequestMapping("/test3")
    public String test3() {
        List<ServiceInstance> instanceList = discoveryClient.getInstances("nacos-provider-1");
        if (CollectionUtils.isEmpty(instanceList)) {
            return "cannot find  service instance from eureka";
        }
        ServiceInstance serviceInstance = instanceList.get(0);
        StringBuffer sb = new StringBuffer();
        sb.append(serviceInstance.getUri()+ "/getTest1");
        String url = sb.toString();
        System.out.println(url);
        String response = restTemplateCustom.getForObject(url, String.class);
        return applicationName + ":" + serverPort + " " + JSON.toJSONString(response) + " " + System.currentTimeMillis();
    }

    @RequestMapping("/test4")
    public String test4() {
        String url = "http://nacos-provider-1/getTest1";
        System.out.println(url);
        String response = restTemplate.getForObject(url, String.class);
        return applicationName + ":" + serverPort + " " + JSON.toJSONString(response) + " " + System.currentTimeMillis();
    }


}
