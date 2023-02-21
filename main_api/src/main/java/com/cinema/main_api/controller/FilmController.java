package com.cinema.main_api.controller;


import com.cinema.main_api.service.FilmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Film;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "电影接口")
@RequestMapping("/api/film")
public class FilmController {

    @Resource
    private FilmService filmService;


    @GetMapping("")
    @ApiOperation("列出所有电影")
    public List<Film> list(@RequestParam(name = "region",required = false) String region,@RequestParam(name = "type",required = false) Integer type){
        if (region != null && !region.equals("全部") && !region.equals("") || type != null) {
            return  filmService.findByRegionAndType(region,type);
        }

        return filmService.findAll();

    }

    @GetMapping("/hot/{limit}")
    @ApiOperation("获取电影热榜")
    public List<Film> listHots(@PathVariable(value = "limit") Integer limit){
        return filmService.findHots(limit);
    }

    @GetMapping("/name/{name}")
    @ApiOperation("搜索电影")
    public List<Film> search(@PathVariable(name = "name") String name){
        return filmService.findLikeName(name);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查找电影")
    public Film findById(@PathVariable(value = "id") Integer id){
        return filmService.findById(id);
    }


}
