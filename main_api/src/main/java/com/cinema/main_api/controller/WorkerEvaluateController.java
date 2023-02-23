package com.cinema.main_api.controller;

import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.main_api.service.WorkerEvaluateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.WorkerEvaluate;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "客服评价接口")
@RequestMapping("/api/we")
public class WorkerEvaluateController {
    @Resource
    private WorkerEvaluateService workerEvaluateService;




    @PostMapping("")
    @ApiOperation(value = "保存客服评价")
    @ApiIdempotence
    @ErrorVoidApi
    public void save(@RequestBody WorkerEvaluate workerEvaluate,@RequestParam("uuid") String uuid)  throws Exception {

        workerEvaluateService.save(workerEvaluate,uuid);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "删除客服评价")
    @ApiIdempotence
    @ErrorVoidApi
    public void deleteById(@PathVariable(value = "id") Long id,@RequestParam("uuid") String uuid)  throws Exception{

        workerEvaluateService.deleteById(id,uuid);
    }

    //==================================================
    @GetMapping("")
    @ApiOperation(value = "查看客服评价")
    public List<WorkerEvaluate> selectList(@RequestParam(name = "wid") Long wid) throws ClassNotFoundException {
        return workerEvaluateService.findByWorkerId(wid);
    }


}
