package com.example.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class TestController {

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private Integer serverPort;

    @Autowired
    @Qualifier("restTemplateCustom") // 表示根据名称来找bean
    private RestTemplate restTemplateCustom;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ProviderClientOne providerClientOne;

    @GetMapping("/test1")
    public String test1() {

        return applicationName + ":" + serverPort + " " + providerClientOne.getTest1() + " " + System.currentTimeMillis();
    }
    @GetMapping("/test2")
    public String test2() {
        List<ServiceInstance> instanceList = discoveryClient.getInstances("eureka-provider-1");
        if (CollectionUtils.isEmpty(instanceList)) {
            return "cannot find  service instance from eureka";
        }
        ServiceInstance serviceInstance = instanceList.get(0);
        StringBuffer sb = new StringBuffer();
        sb.append(serviceInstance.getUri()+ "/getTest1");
        String url = sb.toString();
        System.out.println(url);
        String response = restTemplateCustom.getForObject(url, String.class);
        return applicationName + ":" + serverPort + " " + response + " " + System.currentTimeMillis();
    }


    @GetMapping("/test3")
    public String test3() {
        String url = "http://eureka-provider-1/getTest1";
        System.out.println(url);
        String response = restTemplate.getForObject(url, String.class);
        return applicationName + ":" + serverPort + " " + response + " " + System.currentTimeMillis();
    }

    @GetMapping("/test4")
    public String test4() {
        String url = "http://eureka-provider-1111/getTest1";
        System.out.println(url);
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String response = restTemplate.getForObject(uri, String.class);
        return applicationName + ":" + serverPort + " " + response + " " + System.currentTimeMillis();
    }

}

