package com.cinema.main_api.controller;


import com.cinema.main_api.service.AdminService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "管理员接口")
@RequestMapping("/api/admin")
public class AdminController {

    @Resource
    private AdminService adminService;



    @GetMapping("/{id}")
    public Integer getId(@PathVariable("id") Long id){
        adminService.test(id);

        return 11;
    }
}
