package com.cinema.main_api.controller;

import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.main_api.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Cart;
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

    @PostMapping
    @ApiOperation("添加购物车")
    @ApiIdempotence
    @ErrorVoidApi
    public void save(@RequestBody Cart cart, @RequestParam("uuid") String uuid) throws Exception {
        cartService.save(cart,uuid);

    }

    @DeleteMapping("")
    @ApiOperation("删除购物车")
    @ApiIdempotence
    @ErrorVoidApi
    public void delete(@RequestParam(name = "id") Long id,@RequestParam("uuid") String uuid) throws Exception{


       cartService.deleteById(id,uuid);
    }

    //===========================================================================
    @GetMapping("")
    @ApiOperation("根据用户id查询购物车")
    public List<CartVo> list(@RequestParam(name = "uid") Long uid) throws ClassNotFoundException {
        return cartService.findAllByUserId(uid);
    }
}
