package com.cinema.write_api.controller;


import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.write_api.service.WriteRegistrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Registration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "活动报名接口")
@RequestMapping("/writeapi/registration")
public class WriteRegistrationController {

    @Resource
    private WriteRegistrationService writeRegistrationService;

    @PostMapping("")
    @ApiOperation("报名活动")
    @ApiIdempotence
    @ErrorVoidApi
    public void create(@RequestBody Registration registration,@RequestParam("uuid") String uuid)  throws Exception {
        writeRegistrationService.create(registration);
    }
}
