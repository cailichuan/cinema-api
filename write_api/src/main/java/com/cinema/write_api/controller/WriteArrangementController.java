package com.cinema.write_api.controller;

import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.write_api.service.WriteArrangementService;
import com.cinema.write_api.util.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import model.entity.Arrangement;
import org.springframework.web.bind.annotation.*;
import type.RedisTable;
import util.DataTimeUtil;

import javax.annotation.Resource;


@Slf4j
@RestController
@Api(tags = "电影排片场次的接口")
@RequestMapping("/writeapi/arrangement")
public class WriteArrangementController {

    @Resource
    private WriteArrangementService writeArrangementService;


    @PostMapping("")
    @ApiOperation("新增电影场次")
    @ApiIdempotence
    @ErrorVoidApi
    public void save(@RequestBody Arrangement arrangement,@RequestParam("uuid") String uuid) throws Exception {




        arrangement.setCreateAt(DataTimeUtil.getNowDateTimeString());
        writeArrangementService.save(arrangement);



    }

    @PutMapping("")
    @ApiOperation("修改拍片信息")
    @ApiIdempotence
    public  Arrangement update(@RequestBody Arrangement arrangement,@RequestParam("uuid") String uuid) throws Exception {


        writeArrangementService.Update(arrangement);


        return arrangement;
    }

    @DeleteMapping("")
    @ApiOperation("根据id删除排片")
    @ApiIdempotence
    @ErrorVoidApi
    public void delete(@RequestParam("id") Long id,@RequestParam("uuid") String uuid) throws Exception {

        writeArrangementService.deleteById(id);


    }





}
