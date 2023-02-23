package com.cinema.main_api.controller;

import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.main_api.service.FilmEvaluateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.FilmEvaluate;
import model.vo.FilmEvaluateVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "电影评价接口")
@RequestMapping("/api/fe")
public class FilmEvaluateController {

    @Resource
    private FilmEvaluateService filmEvaluateService;


    @PostMapping("")
    @ApiOperation("评论电影")
    @ApiIdempotence
    @ErrorVoidApi
    public void save(@RequestBody FilmEvaluate filmEvaluate, @RequestParam("uuid") String uuid) throws Exception {
        filmEvaluateService.save(filmEvaluate,uuid);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除评论")
    @ApiIdempotence
    @ErrorVoidApi
    public void remove(@PathVariable(value = "id") Long id,@RequestParam("uuid") String uuid) throws Exception {
        filmEvaluateService.deleteById(id,uuid);
    }

    @DeleteMapping("")
    @ApiOperation("删除该电影的所有评分")
    @ApiIdempotence
    @ErrorVoidApi
    public void removeAll(@RequestParam(name = "fid") Long fid,@RequestParam("uuid") String uuid) throws Exception {
        filmEvaluateService.deleteAllByFilmId(fid,uuid);
    }




    //==================================================
    @GetMapping("")
    @ApiOperation("获取电影评论")
    public List<FilmEvaluateVo> list(@RequestParam(name = "fid") Long fid) throws ClassNotFoundException {

            return filmEvaluateService.findAllByFilmId(fid);

    }
}
