package com.cinema.read_api.controller;


import com.cinema.read_api.service.ReadArrangementService;
import com.cinema.read_api.service.ReadFilmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Arrangement;
import model.vo.ArrangementVo;
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
    @ApiOperation("查询排片")
    public Map<String,Object> findById(@PathVariable(value = "id")Integer id){
        Map<String,Object> map = new HashMap<>();
        Arrangement arrangement = readArrangementService.findById(id);
        map.put("film",readFilmService.findById(arrangement.getFid()));
        map.put("arrangement",arrangement);
        return map;
    }

    @GetMapping("/{id}/getSeats")
    @ApiOperation("获取座位情况")
    public List<Integer> getSeats(@PathVariable(value = "id") Integer id){
        return readArrangementService.getSeatSeatsHaveSelected(id);
    }

    @GetMapping("/film/{fid}")
    @ApiOperation("查询某个电影的所有排片")
    public ArrangementVo findByFilmId(@PathVariable(value = "fid") Integer fid){
        return readArrangementService.findByFilmId(fid);
    }


}
