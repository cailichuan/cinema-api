package com.cinema.write_api.controller;

import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.write_api.service.WriteOrderExceptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.OrderException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "订单异常接口")
@RequestMapping("/writeapi/oe")
public class WriteOrderExceptionController {

    @Resource
    private WriteOrderExceptionService writeOrderExceptionService;

    @PostMapping("")
    @ApiOperation("添加异常订单")
    @ApiIdempotence
    public OrderException create(@RequestBody OrderException orderException,@RequestParam("uuid") String uuid)  throws Exception {
        return writeOrderExceptionService.create(orderException);
    }

    @PutMapping("")
    @ApiOperation("工作人员处理异常订单")
    @ApiIdempotence
    @ErrorVoidApi
    public void handle(@RequestBody OrderException orderException,@RequestParam("uuid") String uuid)  throws Exception {
        writeOrderExceptionService.handleException(orderException);
    }

}
