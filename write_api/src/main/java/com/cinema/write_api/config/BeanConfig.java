package com.cinema.write_api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfig {


    //密码加密bean
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }
}
