package com.cinema.read_api.controller;


import com.cinema.read_api.service.ReadWorkerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Worker;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/readapi/worker")
@Api("客服接口")
public class ReadWorkerController {

    @Resource
    private ReadWorkerService readWorkerService;

    @PostMapping("")
    @ApiOperation("根据id查询客服")
    public Worker findById(@RequestParam(name = "id") Long id){

        return readWorkerService.findById(id);
    }

    @GetMapping("")
    @ApiOperation("查询所有客服")
    public List<Worker> selectList(){

        return readWorkerService.findAll();
    }

}
