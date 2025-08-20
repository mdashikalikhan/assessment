package com.assessment.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/timeout")
public class TimeoutController {

    @GetMapping("/incoming")
    public String incomingTimeout() throws InterruptedException {
        Thread.sleep(10000);
        return "Incoming finish";
    }
}
