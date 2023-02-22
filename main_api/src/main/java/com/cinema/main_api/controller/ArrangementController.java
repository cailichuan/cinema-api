package com.cinema.main_api.controller;

import com.cinema.main_api.service.ArrangementService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import model.entity.Arrangement;
import model.vo.ArrangementVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "电影排片场次接口")
@RequestMapping("/api/arrangement")
public class ArrangementController {

    @Resource
    private ArrangementService arrangementService;

    @PostMapping("")
    @ApiOperation("新增电影场次")
    public void save(@RequestBody Arrangement arrangement){
        arrangementService.save(arrangement);
    }


    @GetMapping("")
    @ApiOperation("列出电影排片")
    public List<Arrangement> list(){
        return arrangementService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("查询排片")
    public Map<String,Object> findById(@PathVariable(value = "id") Long id){
        return  arrangementService.findById(id);
    }

    @GetMapping("/{id}/getSeats")
    @ApiOperation("获取座位情况")
    public List<Integer> getSeats(@PathVariable(value = "id") Long id){
        return arrangementService.getSeatsHaveSelected(id);
    }

    @GetMapping("/film/{fid}")
    @ApiOperation("查询某个电影所有排片")
    public ArrangementVo findByFilmId(@PathVariable(value = "fid") Long fid){
        return arrangementService.findByFilmId(fid);
    }
}
