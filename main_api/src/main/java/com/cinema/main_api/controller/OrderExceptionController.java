package com.cinema.main_api.controller;

import com.cinema.main_api.service.OrderExceptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.OrderException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "订单异常上报接口")
@RequestMapping("/api/oe")
public class OrderExceptionController {

    @Resource
    private OrderExceptionService orderExceptionService;
    @GetMapping("")
    @ApiOperation("查询所有异常订单")
    public List<OrderException> findAll() {
        return orderExceptionService.findAll();
    }

}
