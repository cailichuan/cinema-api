package com.cinema.write_api.controller;

import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.write_api.service.WriteDailyWorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.DailyWork;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "每日工作接口")
@RequestMapping("/writeapi/daily")
public class WriteDailyWorkController {

    @Resource
    private WriteDailyWorkService writeDailyWorkService;

    @PostMapping("")
    @ApiOperation("添加每日工作")
    @ApiIdempotence
    @ErrorVoidApi
    public void create(@RequestBody DailyWork dailyWork,@RequestParam("uuid") String uuid) throws Exception{


        writeDailyWorkService.save(dailyWork);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除每日工作")
    @ApiIdempotence
    @ErrorVoidApi
    public void delete(@PathVariable("id") Long id,@RequestParam("uuid") String uuid) throws Exception{
        writeDailyWorkService.deleteById(id);
    }

}
