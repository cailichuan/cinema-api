package com.cinema.main_api.controller;

import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.main_api.service.DailyWorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.DailyWork;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "每日工作接口")
@RequestMapping("/api/daily")
public class DailyWorkController {

    @Resource
    private DailyWorkService dailyWorkService;

    @PostMapping("")
    @ApiOperation("添加每日工作")
    @ApiIdempotence
    @ErrorVoidApi
    public void create(@RequestBody DailyWork dailyWork, @RequestParam("uuid") String uuid) throws Exception{


       dailyWorkService.save(dailyWork,uuid);
    }


    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除每日工作")
    @ApiIdempotence
    @ErrorVoidApi
    public void delete(@PathVariable("id") Long id,@RequestParam("uuid") String uuid) throws Exception{
       dailyWorkService.deleteById(id,uuid);
    }

    //====================================================================
    @GetMapping("")
    @ApiOperation("查询所有")
    public List<DailyWork> findAll() throws ClassNotFoundException {
        return dailyWorkService.findAll();
    }
}
