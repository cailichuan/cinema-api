package com.cinema.read_api.controller;

import com.cinema.read_api.service.ReadOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.vo.OrderVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<OrderVo> findByUserId(@PathVariable(value = "id") Integer id) {
        return readOrderService.findOrderVoList(id);
    }

}
