package com.assessment.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/leak")
public class LeakController {

    private static List<byte[]> memoryLeakList = new ArrayList<>();

    @GetMapping
    public String leakMemory(){
        byte[] memory = new byte[1024*1024];
        memoryLeakList.add(memory);
        return "Leaked 1MB. Total Objects: " + memoryLeakList.size();

    }

}
