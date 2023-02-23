package com.cinema.read_api.controller;

import com.cinema.read_api.service.ReadWorkerEvaluateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.WorkerEvaluate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "客服评价接口")
@RequestMapping("/readapi/we")
public class ReadWorkerEvaluateController {

    @Resource
    private ReadWorkerEvaluateService readWorkerEvaluateService;

    @GetMapping("")
    @ApiOperation(value = "查看客服评价")
    public List<WorkerEvaluate> selectListByWid(@RequestParam(name = "wid") Long wid) {
        return readWorkerEvaluateService.findByWorkerId(wid);
    }

    @PostMapping("")
    @ApiOperation(value = "根据id查询客服评价")
    public WorkerEvaluate findById(@RequestParam(name = "id") Long id){
        return readWorkerEvaluateService.findById(id);

    }


}
