package com.assessment.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mvc")
 public class BlockingController {

    @GetMapping("/data")
    public String getData() throws InterruptedException {
        Thread.sleep(5000);

        return "Blocking response after 5 seconds";

    }
}
