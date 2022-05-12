package com.example.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
public class TestController {

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private Integer serverPort;


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
}
