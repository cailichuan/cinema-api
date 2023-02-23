package com.cinema.write_api.controller;

import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.write_api.service.WriteFilmEvaluateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.FilmEvaluate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "电影评价接口")
@RequestMapping("/writeapi/fe")
public class WriteFilmEvaluateController {

    @Resource
    private WriteFilmEvaluateService writeFilmEvaluateService;
    @PostMapping("")
    @ApiOperation("评论电影")
    @ApiIdempotence
    @ErrorVoidApi
    public void save(@RequestBody FilmEvaluate filmEvaluate,@RequestParam("uuid") String uuid) throws Exception {
        writeFilmEvaluateService.save(filmEvaluate);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除评论")
    @ApiIdempotence
    @ErrorVoidApi
    public void remove(@PathVariable(value = "id") Long id,@RequestParam("uuid") String uuid) throws Exception {
        writeFilmEvaluateService.deleteById(id);
    }

    @DeleteMapping("")
    @ApiOperation("删除该电影的所有评分")
    @ApiIdempotence
    @ErrorVoidApi
    public void removeAll(@RequestParam(name = "fid") Long fid,@RequestParam("uuid") String uuid) throws Exception {
        writeFilmEvaluateService.deleteAllByFilmId(fid);
    }

}
