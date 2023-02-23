package com.cinema.main_api.controller;


import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import api.write.WriteApi;
import com.cinema.main_api.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Activity;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "活动接口")
@RequestMapping("/api/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;


    @PostMapping("")
    @ApiOperation("新增活动")
    @ApiIdempotence
    @ErrorVoidApi
    public void create(@RequestBody Activity activity,@RequestParam(name = "uuid") String uuid) throws Exception {
        activityService.create(activity,uuid);
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除活动")
    @ApiIdempotence
    @ErrorVoidApi
    public void delete(@PathVariable Long id,@RequestParam(name = "uuid") String uuid) throws Exception{

        activityService.deleteById(id,uuid);
    }
    //======================================================================================
    @GetMapping("")
    @ApiOperation("获取全部活动")
    public List<Activity> findAll() throws Exception {
        return activityService.findAll();
    }

    @GetMapping("{id}")
    @ApiOperation("根据id查找活动")
    public Activity findById(@PathVariable(value = "id") Long id) throws ClassNotFoundException {
        return activityService.finfById(id);
    }



}
