package com.cinema.read_api.controller;


import com.cinema.read_api.mapper.ReadFilmMapper;
import com.cinema.read_api.service.ReadFilmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Film;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "电影接口")
@RequestMapping("/readapi/film")
public class ReadFilmController {

    @Resource
    private ReadFilmService readFilmService;

    @GetMapping("")
    @ApiOperation("列出所有电影")
    public List<Film> list(@RequestParam(name = "region" ,required = false) String region, @RequestParam(name = "type",required = false) String type) {

        Map<String,String> map = new HashMap<>();
        if (region != null && !region.equals("") && !region.equals("全部")){
            map.put("region",region);
        }

        if (type!=null && !type.equals("全部")){
            map.put("type",type);
        }

        return readFilmService.findByMap(map);
    }

    @GetMapping("/hot/{limit}")
    @ApiOperation("获取热榜电影")
    public List<Film> listHots(@PathVariable(value = "limit") Integer limit) {
        Map<String,Integer> map = new HashMap<>();
        map.put("limit",limit);
        return readFilmService.findByMap(map);
    }

    @GetMapping("/name/{name}")
    @ApiOperation("搜索电影")
    public List<Film> search(@PathVariable(value = "name") String name) {
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        return readFilmService.findByMap(map);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查找电影")
    public Film findById(@PathVariable(value = "id") Long id) {
        return readFilmService.findById(id);
    }




}
