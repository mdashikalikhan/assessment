package com.assessment.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/async")
public class AsyncController {

    @GetMapping("/data")
    public CompletableFuture<String> getData() {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
            return "Welcome, ";
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
            return "Async Data";
        });

        CompletableFuture<String> f3 =  CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(12000);
            } catch (InterruptedException e) {

            }
            return "Non blocking data";
        });

        f3.thenAccept(System.out::println);

        f1.exceptionally(Throwable::getMessage).thenCombine(f2, (a, b) -> a + b)
                .thenApply(String::toUpperCase)
                .thenAccept(System.out::println);

        return CompletableFuture.supplyAsync(()->"Async Operation starts");

    }
}
