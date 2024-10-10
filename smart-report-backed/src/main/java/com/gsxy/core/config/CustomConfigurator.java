package com.gsxy.core.config;

import org.springframework.context.annotation.Configuration;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;
import java.util.Collections;

@Configuration
public class CustomConfigurator extends Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        super.modifyHandshake(sec, request, response);

        // 设置允许的源
        response.getHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));

        // 其他跨域相关头部设置
        // response.getHeaders().put("Access-Control-Allow-Headers", "...");
        // response.getHeaders().put("Access-Control-Allow-Methods", "...");
    }
}