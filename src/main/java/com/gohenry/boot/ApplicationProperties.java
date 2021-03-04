package com.gohenry.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "com.gohenry")
@Data
public class ApplicationProperties {

    private String test;
    private Firebase firebase = new Firebase();
    private Mysql mysql = new Mysql();

    @Data
    public static class Firebase {
        private String url;

        private String user;
    }

    @Data
    public static class Mysql {

        private String url;

        private String host;

        private int port;
    }
}
