package com.cinema.main_api.controller;

import com.cinema.main_api.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Order;
import model.vo.OrderVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "订单接口")
@RequestMapping("/api/order")
public class OrderController {

    @Resource
    private OrderService orderService;
    @GetMapping("")
    @ApiOperation(value = "查询所有订单")
    public List<OrderVo> findAll() {
        return orderService.findAll();
    }



    @GetMapping("/user/{id}")
    @ApiOperation(value = "查询用户订单")
    public List<OrderVo> findByUser(@PathVariable(value = "id") Long id) {
        return orderService.findByUser(id);
    }

}
