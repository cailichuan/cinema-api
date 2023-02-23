package com.cinema.read_api.controller;


import com.cinema.read_api.service.ReadArrangementService;
import com.cinema.read_api.service.ReadFilmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Arrangement;
import model.entity.Film;
import model.vo.ArrangementVo;
import model.vo.OneArrangementVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "电影排片场次接口")
@RequestMapping("/readapi/arrangement")
public class ReadArrangementController {

    @Resource
    private ReadArrangementService readArrangementService;

    @Resource
    private ReadFilmService readFilmService;

    @GetMapping("")
    @ApiOperation("列出电影列表")
    public List<Arrangement> finAll(){
        return readArrangementService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询排片")
    public OneArrangementVo findById(@PathVariable(value = "id")Long id){
        Map<String,Object> map = new HashMap<>();
        Arrangement arrangement = readArrangementService.findById(id);
        Film film = readFilmService.findById(arrangement.getFid());
        OneArrangementVo oneArrangementVo = new OneArrangementVo(film, arrangement);

        return oneArrangementVo;
    }

    @GetMapping("/{id}/getSeats")
    @ApiOperation("获取座位情况")
    public List<Integer> getSeats(@PathVariable(value = "id") Long id){
        return readArrangementService.getSeatSeatsHaveSelected(id);
    }

    @GetMapping("/film/{fid}")
    @ApiOperation("查询某个电影的所有排片")
    public ArrangementVo findByFilmId(@PathVariable(value = "fid") Long fid){
        return readArrangementService.findByFilmId(fid);
    }


}
