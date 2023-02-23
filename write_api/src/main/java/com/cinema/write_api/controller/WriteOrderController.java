package com.cinema.write_api.controller;

import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.write_api.service.WriteOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Cart;
import model.entity.Order;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "订单接口")
@RequestMapping("/writeapi/order")
public class WriteOrderController {

    @Resource
    private WriteOrderService writeOrderService;






    @PostMapping("")
    @ApiOperation(value = "创建订单")
    @ApiIdempotence
    @ErrorVoidApi
    public void save(@RequestBody Cart cart,@RequestParam("uuid") String uuid)  throws Exception {
        writeOrderService.create(cart);
    }

    @PutMapping("")
    @ApiOperation(value = "修改订单")
    @ApiIdempotence
    @ErrorVoidApi
    public void update(@RequestBody Order order,@RequestParam("uuid") String uuid)  throws Exception {
        writeOrderService.update(order);
    }


    @GetMapping("/pay")
    @ApiOperation(value = "支付订单")
    public Order save(@RequestParam(name = "id") Long id)  throws Exception {


        return writeOrderService.pay(id);
    }
}
