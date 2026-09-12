package com.subhakar.springbootlearning.user.service;

import com.subhakar.springbootlearning.user.dto.ThankRequest;
import com.subhakar.springbootlearning.user.dto.ThankResponse;
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
