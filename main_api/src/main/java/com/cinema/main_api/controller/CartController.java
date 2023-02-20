package com.cinema.main_api.controller;

import com.cinema.main_api.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.vo.CartVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "用户购物车接口")
@RequestMapping("/api/cart")
public class CartController {

    @Resource
    private CartService cartService;

    @GetMapping("")
    @ApiOperation("根据用户id查询购物车")
    public List<CartVo> list(@RequestParam(name = "uid") Integer uid){
        return cartService.findAllByUserId(uid);
    }
}
