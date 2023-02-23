package com.cinema.read_api.controller;

import com.cinema.read_api.service.ReadFilmEvaluateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.FilmEvaluate;
import model.vo.FilmEvaluateVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "电影评价接口")
@RequestMapping("/readapi/fe")
public class ReadFilmEvaluateController {

    @Resource
    private ReadFilmEvaluateService readFilmEvaluateService;

    @GetMapping("")
    @ApiOperation("获取电影评论")
    public List<FilmEvaluateVo> list(@RequestParam(name = "fid") Long fid) {

            return readFilmEvaluateService.findAllByFilmId(fid);

    }


    @PostMapping("")
    @ApiOperation("根据id获取电影评论")
    public FilmEvaluate findById(@RequestParam(name = "id") Long id){

        return readFilmEvaluateService.findById(id);
    }
}
