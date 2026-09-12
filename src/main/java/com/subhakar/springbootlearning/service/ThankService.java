package com.subhakar.springbootlearning.service;

import com.subhakar.springbootlearning.dto.ThankRequest;
import com.subhakar.springbootlearning.dto.ThankResponse;
import org.springframework.stereotype.Service;

@Service
public class ThankService {

    public ThankResponse thank(ThankRequest request) {

        return new ThankResponse(
                "Hey " + request.name() +
                        ", Thankyou for visting"
        );
    }
}
