package com.cinema.write_api.controller;

import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.write_api.service.WriteWorkerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Worker;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "员工接口")
@RequestMapping("/writeapi/worker")
public class WriteWorkerController {

    @Resource
    private WriteWorkerService writeWorkerService;


    @PostMapping("")
    @ApiOperation("添加员工")
    @ApiIdempotence
    public Worker create(@RequestBody Worker worker,@RequestParam("uuid") String uuid)  throws Exception {
        return writeWorkerService.create(worker);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除员工")
    @ApiIdempotence
    @ErrorVoidApi
    public void deleteById(@PathVariable(value = "id") Long id,@RequestParam("uuid") String uuid)  throws Exception{
        writeWorkerService.deleteById(id);
    }

    @PutMapping("")
    @ApiOperation("更新员工信息")
    @ApiIdempotence
    @ErrorVoidApi
    public void update(@RequestBody Worker worker,@RequestParam("uuid") String uuid)  throws Exception {
       writeWorkerService.update(worker);
    }
}
