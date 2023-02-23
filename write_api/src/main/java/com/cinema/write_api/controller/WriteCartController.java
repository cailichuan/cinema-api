package com.cinema.write_api.controller;


import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.write_api.service.WriteCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Cart;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "用户购物车接口")
@RequestMapping("/writeapi/cart")
public class WriteCartController {

    @Resource
    private WriteCartService writeCartService;

    @PostMapping
    @ApiOperation("添加购物车")
    @ApiIdempotence
    @ErrorVoidApi
    public void save(@RequestBody Cart cart,@RequestParam("uuid") String uuid) throws Exception {
        writeCartService.save(cart);

    }

    @DeleteMapping("")
    @ApiOperation("删除购物车")
    @ApiIdempotence
    @ErrorVoidApi
    public void delete(@RequestParam(name = "id") Long id,@RequestParam("uuid") String uuid) throws Exception{


        writeCartService.deleteById(id);
    }


}
