package com.cinema.write_api.controller;


import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.write_api.service.WriteFilmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Film;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "电影接口")
@RequestMapping("/writeapi/film")
public class WriteFilmController {

    @Resource
    private WriteFilmService writeFilmService;

    @PostMapping("")
    @ApiOperation(value = "保存电影")
    @ApiIdempotence
    @ErrorVoidApi
    public void save(@RequestBody Film film,@RequestParam("uuid") String uuid) throws Exception {

        writeFilmService.save(film);
    }

    @PutMapping("")
    @ApiOperation(value = "更新电影")
    @ApiIdempotence
    @ErrorVoidApi
    public void update(@RequestBody Film film,@RequestParam("uuid") String uuid)throws Exception {
        writeFilmService.update(film);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除电影")
    @ApiIdempotence
    @ErrorVoidApi
    public void deleteById(@PathVariable(value = "id") Long id,@RequestParam("uuid") String uuid) throws Exception {
        writeFilmService.deleteById(id);
    }

}
