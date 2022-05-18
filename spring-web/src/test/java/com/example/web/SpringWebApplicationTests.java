package com.example.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.URISyntaxException;

class SpringWebApplicationTests {

    @Test
    void contextLoads() throws URISyntaxException {
        URI uri = new URI("http://sfda/adfa");
        String host = uri.getHost();
        System.out.println(uri.toString() + " host " + host);
    }

}
