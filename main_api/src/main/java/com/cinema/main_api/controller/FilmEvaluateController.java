package com.cinema.main_api.controller;

import com.cinema.main_api.service.FilmEvaluateService;
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
@RequestMapping("/api/fe")
public class FilmEvaluateController {

    @Resource
    private FilmEvaluateService filmEvaluateService;

    @GetMapping("")
    @ApiOperation("获取电影评论")
    public List<FilmEvaluateVo> list(@RequestParam(name = "fid") Integer fid) {
        if (fid != null) {
            return filmEvaluateService.findAllByFilmId(fid);
        }
        return null;
    }
}
