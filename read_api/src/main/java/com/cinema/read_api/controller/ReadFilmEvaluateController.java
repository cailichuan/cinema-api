package com.cinema.read_api.controller;

import com.cinema.read_api.service.ReadFilmEvaluateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.vo.FilmEvaluateVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        if (fid != null) {
            return readFilmEvaluateService.findAllByFilmId(fid);
        }
        return null;
    }
}
