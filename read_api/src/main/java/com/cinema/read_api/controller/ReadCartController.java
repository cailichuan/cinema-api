package com.cinema.read_api.controller;

import com.cinema.read_api.service.ReadCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Cart;
import model.vo.CartVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "用户购物车接口")
@RequestMapping("/readapi/cart")
public class ReadCartController {

    @Resource
    private ReadCartService readCartService;

    @GetMapping("")
    @ApiOperation("根据用户id查询购物车")
    public List<CartVo> list(@RequestParam(name = "uid") Long uid){

        return readCartService.findAllByUserId(uid);

    }

    @PostMapping("")
    @ApiOperation("根据id查询购物车")
    public Cart findById(@RequestParam(name = "id") Long id){
        return readCartService.findById(id);
    }
}
