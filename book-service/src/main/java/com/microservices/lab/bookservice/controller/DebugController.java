package com.microservices.lab.bookservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/debug")
public class DebugController {

    @Value("${server.port:8081}")
    private String serverPort;

    @Value("${HOSTNAME:unknown}")
    private String hostname;

    @GetMapping("/instance")
    public ResponseEntity<Map<String, String>> getInstanceInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("hostname", hostname);
        info.put("port", serverPort);
        info.put("instance", hostname + ":" + serverPort);
        return ResponseEntity.ok(info);
    }
}

