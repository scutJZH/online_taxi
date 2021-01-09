package com.onlinetaxi.userauthority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserAuthorityApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserAuthorityApplication.class, args);
    }
}
