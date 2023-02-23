package com.cinema.main_api.controller;

import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.main_api.service.RegistrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Registration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "活动报名接口")
@RequestMapping("/api/registration")
public class RegistrationController {

    @Resource
    private RegistrationService registrationService;


    @PostMapping("")
    @ApiOperation("报名活动")
    @ApiIdempotence
    @ErrorVoidApi
    public void create(@RequestBody Registration registration, @RequestParam("uuid") String uuid)  throws Exception {
        registrationService.create(registration,uuid);
    }
    //====================================================================
    @GetMapping("")
    @ApiOperation("查询所有报名信息")
    public List<Registration> create() throws ClassNotFoundException {
        return registrationService.findAll();
    }


}


