package com.cinema.main_api.controller;

import com.cinema.main_api.service.DailyWorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.DailyWork;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "每日工作接口")
@RequestMapping("/api/daily")
public class DailyWorkController {

    @Resource
    private DailyWorkService dailyWorkService;

    @GetMapping("")
    @ApiOperation("查询所有")
    public List<DailyWork> findAll(){
        return dailyWorkService.findAll();
    }
}
