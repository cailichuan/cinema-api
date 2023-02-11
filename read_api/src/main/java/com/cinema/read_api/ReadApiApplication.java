package com.cinema.read_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class ReadApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadApiApplication.class, args);
    }

}
