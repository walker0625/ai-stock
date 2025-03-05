package com.walker.aistock.backend.common.controller;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @Value("${server.env}")
    private String env;

    @Value("${server.port}")
    private String serverPort;

    @Value("${server.serverAddress}")
    private String serverAddress;

    @Value("${serverName}")
    private String serverName;

    @GetMapping("/hc")
    public ResponseEntity<?> healthCheck() {

        Map<String, String> serverInfo = new TreeMap<>();
        serverInfo.put("serverName", serverName);
        serverInfo.put("serverAddress", serverAddress);
        serverInfo.put("serverPort", serverPort);
        serverInfo.put("env", env);

        return ResponseEntity.ok(serverInfo);
    }

    @GetMapping("/env")
    public ResponseEntity<?> env() {
        return ResponseEntity.ok(env);
    }

}