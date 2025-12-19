package com.sasmithx.hateoas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {
    @RequestMapping
    @GetMapping
    public String checkHealth() {
        return "App is running fine";
    }
}
