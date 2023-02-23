package com.cinema.main_api.controller;


import annotation.DisableBaseResponse;
import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.cinema.main_api.service.UuidService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/uuid")
@Api(tags = "uuid的接口")

public class UuidController {

    @Resource
    private UuidService uuidService;

    @GetMapping("")
    @ApiOperation(value = "获取uuid")
    @DisableBaseResponse
    public Object getUuid(){

        String uuid=uuidService.getUUid();



        return uuid;
    }
}
