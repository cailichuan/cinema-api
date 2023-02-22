package com.cinema.read_api.controller;

import com.cinema.read_api.service.ReadCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.vo.CartVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
