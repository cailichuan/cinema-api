package com.cinema.write_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableTransactionManagement
@EnableEurekaClient
public class WriteApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WriteApiApplication.class, args);
    }

}
