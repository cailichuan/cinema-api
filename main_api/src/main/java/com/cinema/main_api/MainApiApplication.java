package com.cinema.main_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients(basePackages = {"api.read","api.write"})
public class MainApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(MainApiApplication.class, args);
    }

}
