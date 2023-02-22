package com.cinema.read_api.controller;

import com.cinema.read_api.service.ReadRegistrationService;
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
@RequestMapping("/readapi/registration")
public class ReadRegistrationController {

    @Resource
    private ReadRegistrationService readRegistrationService;

    @GetMapping("")
    @ApiOperation("查询所有报名信息")
    public List<Registration> create() {
        return readRegistrationService.findAll();
    }

}
