package com.cinema.write_api.controller;


import com.cinema.write_api.service.WriteActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Activity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags="活动接口")
@RequestMapping("/writeapi/activity")
public class WriteActivityController {
    @Resource
    private WriteActivityService writeActivityService;

    @PostMapping("")
    @ApiOperation("新增活动")
    public void create(@RequestBody Activity activity){
        writeActivityService.create(activity);
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除活动")
    public void delete(@PathVariable Long id){
        writeActivityService.deleteById(id);
    }
}
