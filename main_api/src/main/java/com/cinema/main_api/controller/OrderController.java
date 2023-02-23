package com.cinema.main_api.controller;

import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.main_api.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Cart;
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



    @PostMapping("")
    @ApiOperation(value = "创建订单")
    @ApiIdempotence
    @ErrorVoidApi
    public void save(@RequestBody Cart cart, @RequestParam("uuid") String uuid)  throws Exception {
        orderService.create(cart,uuid);
    }

    @PutMapping("")
    @ApiOperation(value = "修改订单")
    @ApiIdempotence
    @ErrorVoidApi
    public void update(@RequestBody Order order,@RequestParam("uuid") String uuid)  throws Exception {
        orderService.update(order,uuid);
    }


    @GetMapping("/pay")
    @ApiOperation(value = "支付订单")
    public Order pay(Long id) throws Exception {
        return orderService.pay(id);
    }
    //=====================================================================
    @GetMapping("")
    @ApiOperation(value = "查询所有订单")
    public List<OrderVo> findAll() throws ClassNotFoundException {
        return orderService.findAll();
    }



    @GetMapping("/user/{id}")
    @ApiOperation(value = "查询用户订单")
    public List<OrderVo> findByUser(@PathVariable(value = "id") Long id) throws ClassNotFoundException {
        return orderService.findByUser(id);
    }





}
