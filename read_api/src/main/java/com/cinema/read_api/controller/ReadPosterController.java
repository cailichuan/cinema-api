package com.cinema.read_api.controller;

import com.cinema.read_api.service.ReadOrderService;
import com.cinema.read_api.service.ReadPosterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Poster;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    @ApiOperation("根据id获取海报")
    public Poster findById(@RequestParam(name = "id") Long id){

        Map<String,Long> map = new HashMap<>();
        map.put("id",id);
        List<Poster> poster = readPosterService.findByMap(map);

        return poster==null? null:poster.get(0);

    }
}
