package com.cinema.read_api.controller;

import api.read.ReadApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TestController{



    @GetMapping("/read/getdata")
    public String getTestData() {



            return "ok";

    }
}
