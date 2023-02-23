package com.cinema.write_api.controller;


import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.write_api.service.WriteWorkerEvaluateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.WorkerEvaluate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "客服评价接口")
@RequestMapping("/writeapi/we")
public class WriteWorkerEvaluateController {

    @Resource
    private WriteWorkerEvaluateService writeWorkerEvaluateService;
    @PostMapping("")
    @ApiOperation(value = "保存客服评价")
    @ApiIdempotence
    @ErrorVoidApi
    public void save(@RequestBody WorkerEvaluate workerEvaluate,@RequestParam("uuid") String uuid)  throws Exception {

        writeWorkerEvaluateService.save(workerEvaluate);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "删除客服评价")
    @ApiIdempotence
    @ErrorVoidApi
    public void deleteById(@PathVariable(value = "id") Long id,@RequestParam("uuid") String uuid)  throws Exception{

        writeWorkerEvaluateService.deleteById(id);
    }

}
