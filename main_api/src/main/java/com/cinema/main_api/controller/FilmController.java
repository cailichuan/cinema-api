package com.cinema.main_api.controller;


import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
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


    @PostMapping("")
    @ApiOperation(value = "保存电影")
    @ApiIdempotence
    @ErrorVoidApi
    public void save(@RequestBody Film film,@RequestParam("uuid") String uuid) throws Exception {

       filmService.save(film,uuid);
    }

    @PutMapping("")
    @ApiOperation(value = "更新电影")
    @ApiIdempotence
    @ErrorVoidApi
    public void update(@RequestBody Film film,@RequestParam("uuid") String uuid)throws Exception {
        filmService.update(film,uuid);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除电影")
    @ApiIdempotence
    @ErrorVoidApi
    public void deleteById(@PathVariable(value = "id") Long id,@RequestParam("uuid") String uuid) throws Exception {
        filmService.deleteById(id,uuid);
    }



    //===============================================================================
    @GetMapping("")
    @ApiOperation("列出所有电影")
    public List<Film> list(@RequestParam(name = "region",required = false) String region,@RequestParam(name = "type",required = false) String type) throws ClassNotFoundException {
        if (region != null && !region.equals("全部") && !region.equals("") || type != null) {
            return  filmService.findByRegionAndType(region,type);
        }

        return filmService.findAll();

    }

    @GetMapping("/hot/{limit}")
    @ApiOperation("获取电影热榜")
    public List<Film> listHots(@PathVariable(value = "limit") Integer limit) throws ClassNotFoundException {
        return filmService.findHots(limit);
    }

    @GetMapping("/name/{name}")
    @ApiOperation("搜索电影")
    public List<Film> search(@PathVariable(name = "name") String name) throws ClassNotFoundException {
        return filmService.findLikeName(name);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查找电影")
    public Film findById(@PathVariable(value = "id") Long id) throws ClassNotFoundException {
        return filmService.findById(id);
    }


}
