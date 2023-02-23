package com.cinema.main_api.controller;

import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.main_api.service.ArrangementService;

import com.cinema.main_api.util.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import model.entity.Arrangement;
import model.vo.ArrangementVo;
import model.vo.OneArrangementVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@Api(tags = "电影排片场次接口")
@RequestMapping("/api/arrangement")
public class ArrangementController {

    @Resource
    private ArrangementService arrangementService;



    @PostMapping("")
    @ApiOperation("新增电影场次")
    @ErrorVoidApi
    @ApiIdempotence
    public void save(@RequestBody Arrangement arrangement,@RequestParam(name = "uuid") String uuid) throws Exception {
        arrangementService.save(arrangement,uuid);
    }


    @PutMapping("")
    @ApiOperation("修改排片信息")
    @ApiIdempotence
    public Arrangement update(@RequestBody Arrangement arrangement,@RequestParam("uuid") String uuid) throws Exception{
        arrangementService.Update(arrangement,uuid);
        return arrangement;
    }


    @DeleteMapping("")
    @ApiOperation("根据id删除排片")
    @ErrorVoidApi
    public void delete(@RequestParam("id") Long id,@RequestParam("uuid") String uuid) throws Exception {

       arrangementService.deleteById(id,uuid);
    }
    //===========================================================================


    @GetMapping("")
    @ApiOperation("列出电影排片")
    public List<Arrangement> list() throws ClassNotFoundException {
        return arrangementService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("查询排片")
    public OneArrangementVo findById(@PathVariable(value = "id") Long id) throws ClassNotFoundException {
        return  arrangementService.findById(id);
    }

    @GetMapping("/{id}/getSeats")
    @ApiOperation("获取座位情况")
    public List<Integer> getSeats(@PathVariable(value = "id") Long id){
        return arrangementService.getSeatsHaveSelected(id);
    }

    @GetMapping("/film/{fid}")
    @ApiOperation("查询某个电影所有排片")
    public ArrangementVo findByFilmId(@PathVariable(value = "fid") Long fid) throws ClassNotFoundException {
        return arrangementService.findByFilmId(fid);
    }
}
