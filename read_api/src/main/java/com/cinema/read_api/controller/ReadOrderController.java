package com.cinema.read_api.controller;

import com.cinema.read_api.service.ReadOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Order;
import model.vo.OrderVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "订单接口")
@RequestMapping("/readapi/order")
public class ReadOrderController {

    @Resource
    private ReadOrderService readOrderService;

    @GetMapping("")
    @ApiOperation(value = "查询所有订单")
    public List<OrderVo> findAll() {
        return readOrderService.findOrderVoList(null);
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "查询用户订单")
    public List<OrderVo> findByUserId(@PathVariable(value = "id") Long id) {
        return readOrderService.findOrderVoList(id);
    }

    @PostMapping("")
    @ApiOperation("根据订单id查询订单")
    public Order findById(@RequestParam(name = "id") Long id){
        return readOrderService.findOrderById(id);
    }

}
