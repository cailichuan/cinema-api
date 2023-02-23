package com.cinema.main_api.controller;

import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.main_api.service.OrderExceptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.OrderException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "订单异常上报接口")
@RequestMapping("/api/oe")
public class OrderExceptionController {

    @Resource
    private OrderExceptionService orderExceptionService;


    @PostMapping("")
    @ApiOperation("添加异常订单")
    @ApiIdempotence
    public OrderException create(@RequestBody OrderException orderException, @RequestParam("uuid") String uuid)  throws Exception {
        return orderExceptionService.create(orderException,uuid);
    }

    @PutMapping("")
    @ApiOperation("工作人员处理异常订单")
    @ApiIdempotence
    @ErrorVoidApi
    public void handle(@RequestBody OrderException orderException,@RequestParam("uuid") String uuid)  throws Exception {
        orderExceptionService.handleException(orderException,uuid);
    }


    //========================================================================
    @GetMapping("")
    @ApiOperation("查询所有异常订单")
    public List<OrderException> findAll() throws ClassNotFoundException {
        return orderExceptionService.findAll();
    }

}
