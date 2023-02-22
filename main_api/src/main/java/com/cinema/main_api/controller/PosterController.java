package com.cinema.main_api.controller;

import com.cinema.main_api.service.PosterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Poster;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "首页海报接口")
@RequestMapping("/api/poster")
public class PosterController {
    @Resource
    private PosterService posterService;
    @GetMapping("")
    @ApiOperation("获取所有海报")
    public List<Poster> list(@RequestParam(name = "status",required = false) Boolean status) {

        return posterService.findByMap(status);
    }

}
