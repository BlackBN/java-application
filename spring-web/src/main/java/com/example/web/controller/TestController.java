package com.example.web.controller;

import com.example.web.pojo.SidecarReq;
import com.example.web.pojo.SidecarResp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class TestController {

    @PostMapping("/register")
    public SidecarResp register(@RequestBody SidecarReq req) {
        return new SidecarResp("register", new ArrayList());
    }

    @PostMapping("/discovery")
    public SidecarResp discovery(@RequestBody SidecarReq req) {
        return new SidecarResp(req.getName(), new ArrayList(){{
            add("127.0.0.1:8710");
        }});
    }

    @PostMapping("/heartbeat")
    public SidecarResp heartbeat(@RequestBody SidecarReq req) {
        return new SidecarResp("register", new ArrayList());
    }

    @PostMapping("/healthz")
    public SidecarResp healthz(@RequestBody SidecarReq req) {
        return new SidecarResp("register", new ArrayList());
    }

    @PostMapping("/deregister")
    public SidecarResp deregister(@RequestBody SidecarReq req) {
        return new SidecarResp("register", new ArrayList());
    }
}
