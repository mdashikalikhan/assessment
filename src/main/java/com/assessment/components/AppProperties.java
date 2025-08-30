package com.assessment.components;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
@Data
public class AppProperties {

    private String name;
    private String version;

    private Server server;

    @Data
    public static class Server{
        private String host;
        private int port;
    }
}
