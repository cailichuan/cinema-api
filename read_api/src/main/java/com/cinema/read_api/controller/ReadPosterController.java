package com.cinema.read_api.controller;

import com.cinema.read_api.service.ReadOrderService;
import com.cinema.read_api.service.ReadPosterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Poster;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "首页海报接口")
@RequestMapping("/readapi/poster")
public class ReadPosterController {
    @Resource
    private ReadPosterService readPosterService;

    @GetMapping("")
    @ApiOperation("获取所有海报")
    public List<Poster> list(@RequestParam(name = "status",required = false) Boolean status) {
        Map<String,Boolean> map=new HashMap<>();
        if (status != null) {

            map.put("status",status);

        }
        return readPosterService.findByMap(map);
    }
}
