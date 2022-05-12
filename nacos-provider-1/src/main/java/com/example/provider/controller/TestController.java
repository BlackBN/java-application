package com.example.provider.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


@RestController
public class TestController implements CommandLineRunner {
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private Integer serverPort;

    @NacosInjected
    private NamingService namingService;

    @GetMapping("/getTest1")
    public String getTest1(HttpServletRequest request) {
        Enumeration<String> headersDyeing = request.getHeaders("X-Ploto-Dyeing");
        StringBuilder sb = new StringBuilder();
        sb.append("X-Ploto-Dyeing : [");
        while (headersDyeing.hasMoreElements()) {
            sb.append(headersDyeing.nextElement()).append(" ");
        }
        sb.append("],  Host : [");
        Enumeration<String> headersHost = request.getHeaders("Host");
        while (headersHost.hasMoreElements()) {
            sb.append(headersHost.nextElement()).append(" ");
        }
        sb.append("]");
        return "call " +
                applicationName + ":" +
                serverPort + " getTest1 method success!!! " +
                "header info is : " + sb.toString();
    }

    @Override
    public void run(String... args) throws Exception {
        // 通过Naming服务注册实例到注册中心
        namingService.registerInstance(applicationName, "127.0.0.1", serverPort);
    }
}
