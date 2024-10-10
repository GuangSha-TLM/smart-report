package com.gsxy.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
public class CoreApplication {
    public static void main(String[] args) {
        System.out.println("hello , 信息学院专业社团");
        SpringApplication.run(CoreApplication.class, args);
    }
}
