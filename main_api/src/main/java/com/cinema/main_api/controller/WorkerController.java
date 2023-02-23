package com.cinema.main_api.controller;


import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.main_api.service.WorkerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Worker;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/worker")
@Api("客服接口")
public class WorkerController {

    @Resource
    private WorkerService workerService;



    @PostMapping("")
    @ApiOperation("添加员工")
    @ApiIdempotence
    public Worker create(@RequestBody Worker worker,@RequestParam("uuid") String uuid)  throws Exception {
        return workerService.create(worker,uuid);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除员工")
    @ApiIdempotence
    @ErrorVoidApi
    public void deleteById(@PathVariable(value = "id") Long id,@RequestParam("uuid") String uuid)  throws Exception{
        workerService.deleteById(id,uuid);
    }

    @PutMapping("")
    @ApiOperation("更新员工信息")
    @ApiIdempotence
    @ErrorVoidApi
    public void update(@RequestBody Worker worker,@RequestParam("uuid") String uuid)  throws Exception {
        workerService.update(worker,uuid);
    }

    //=====================================================================================
    @GetMapping("/{id}")
    @ApiOperation("根据id获取客服")
    public Worker findById(@PathVariable("id") Long id) throws ClassNotFoundException {

        return workerService.findById(id);
    }

    @GetMapping("")
    @ApiOperation("获取所有客服")
    public List<Worker> selectList() throws ClassNotFoundException {
        return workerService.findAll();
    }


}
