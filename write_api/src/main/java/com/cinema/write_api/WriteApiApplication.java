package com.cinema.write_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WriteApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WriteApiApplication.class, args);
    }

}
