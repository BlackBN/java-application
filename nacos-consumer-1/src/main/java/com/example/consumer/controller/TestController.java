package com.example.consumer.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.List;


@RestController
public class TestController implements CommandLineRunner {
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private Integer serverPort;

    @NacosInjected
    private NamingService namingService;

    @GetMapping("/test1")
    public String getTest1(HttpServletRequest request) throws IOException {
        List<Instance> instanceList = null;
        try {
            instanceList = namingService.selectInstances("nacos-provider-1", true);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        if (instanceList != null && instanceList.size() > 0) {
            Instance instance = instanceList.get(0);
            String url = "http://" + instance.getIp() + ":" + instance.getPort() + "/getTest1";
            StringBuilder result = new StringBuilder();
            BufferedReader in = null;
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.connect();
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            return "call " +
                    applicationName + ":" +
                    serverPort + " getTest1 method success!!! " + "response : " + result +
                    "  header info is : ";
        }else{
            return "call " +
                    applicationName + ":" +
                    serverPort + "  error";
        }

    }

    @Override
    public void run(String... args) throws Exception {
        // 通过Naming服务注册实例到注册中心
        namingService.registerInstance(applicationName, "127.0.0.1", serverPort);
    }
}
