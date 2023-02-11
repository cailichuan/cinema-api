package com.cinema.read_api.controller;

import com.cinema.read_api.service.ReadActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Activity;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags="活动接口")
@RequestMapping("/readapi/activity")
public class ReadActivityController {
    @Resource
    private ReadActivityService readActivityService;

    @GetMapping ("")
    @ApiOperation("获取全部活动")
    public List<Activity> findAll(){
        return readActivityService.findAll();
    }

    @GetMapping("{id}")
    @ApiOperation("根据id查找活动")
    public Activity findById(@PathVariable("id") Integer id){
        return readActivityService.finfById(id);
    }
}
