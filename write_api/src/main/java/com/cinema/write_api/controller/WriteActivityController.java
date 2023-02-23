package com.cinema.write_api.controller;


import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.write_api.service.WriteActivityService;
import com.cinema.write_api.util.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import model.entity.Activity;
import org.springframework.web.bind.annotation.*;
import type.RedisTable;

import javax.annotation.Resource;

@Slf4j
@RestController
@Api(tags="活动接口")
@RequestMapping("/writeapi/activity")
public class WriteActivityController {
    @Resource
    private WriteActivityService writeActivityService;



    @PostMapping("")
    @ApiOperation("新增活动")
    @ApiIdempotence
    @ErrorVoidApi
    public void create(@RequestBody Activity activity,@RequestParam(name = "uuid") String uuid) throws Exception {


        writeActivityService.create(activity);


    }

    @DeleteMapping("{id}")
    @ApiOperation("删除活动")
    @ApiIdempotence
    @ErrorVoidApi
    public void delete(@PathVariable(value = "id") Long id,@RequestParam(name = "uuid") String uuid) throws Exception {


        writeActivityService.deleteById(id);

    }
}
