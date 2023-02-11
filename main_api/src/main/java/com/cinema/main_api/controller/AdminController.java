package com.cinema.main_api.controller;


import com.cinema.main_api.service.AdminService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "管理员接口")
@RequestMapping("/api/admin")
public class AdminController {

    @Resource
    private AdminService adminService;


}
