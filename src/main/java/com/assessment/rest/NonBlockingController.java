package com.assessment.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/webflux")
public class NonBlockingController {

    @GetMapping("/data") public Mono<String> getData() {
        return Mono.delay(Duration.ofSeconds(5))
                .map(t-> "Non blocking response after 5 seconds.");
    }
}
