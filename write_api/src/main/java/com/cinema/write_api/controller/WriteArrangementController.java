package com.cinema.write_api.controller;

import com.cinema.write_api.service.WriteArrangementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Arrangement;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "电影排片场次的接口")
@RequestMapping("/writeapi/arrangement")
public class WriteArrangementController {

    @Resource
    private WriteArrangementService writeArrangementService;

    @PostMapping("")
    @ApiOperation("新增电影场次")
    public void save(@RequestBody Arrangement arrangement){
        writeArrangementService.save(arrangement);

    }

    @PutMapping("")
    @ApiOperation("修改拍片信息")
    public  Arrangement update(@RequestBody Arrangement arrangement){
        writeArrangementService.Update(arrangement);
        return arrangement;
    }

    @DeleteMapping("")
    @ApiOperation("根据id删除排片")
    public void delete(@RequestParam("id") Integer id){
        writeArrangementService.deleteById(id);
    }





}
