package com.subhakar.springbootlearning.controller;

import com.subhakar.springbootlearning.dto.ThankRequest;
import com.subhakar.springbootlearning.dto.ThankResponse;
import com.subhakar.springbootlearning.service.ThankService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/thank")
public class ThankController {
    private final ThankService thankService;

    public ThankController(ThankService thankService) {
        this.thankService = thankService;
    }

    @PostMapping
    public ThankResponse thank(@RequestBody ThankRequest request) {
        return thankService.thank(request);
    }
}
