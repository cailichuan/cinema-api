package com.cinema.main_api.controller;

import com.cinema.main_api.service.RegistrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "活动接口")
@RequestMapping("/api/registration")
public class RegistrationController {

    @Resource
    private RegistrationService registrationService;

    @GetMapping("")
    @ApiOperation("查询所有报名信息")
    public List<Registration> create() {
        return registrationService.findAll();
    }


}


