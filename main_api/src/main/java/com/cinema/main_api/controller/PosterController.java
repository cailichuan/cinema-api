package com.cinema.main_api.controller;

import annotation.*;
import com.cinema.main_api.service.PosterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.dto.PosterDto;
import model.entity.Poster;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "首页海报接口")
@RequestMapping("/api/poster")
public class PosterController {
    @Resource
    private PosterService posterService;


    @PostMapping("")
    @ApiOperation("添加首页海报")
    @PostApiIdempotence
    @ErrorVoidApi
    public void save(@ErrorVoidApiParamter @PostApiIP PosterDto posterDto, MultipartFile file)  throws Exception {





        if (file == null) throw new Exception("请求参数缺失");

        if (file.isEmpty()) {
            throw new Exception("上传失败，请选择文件");
        }




        posterService.save(posterDto,file);
    }

    @PutMapping("")
    @ApiOperation("更新海报")
    @PostApiIdempotence
    @ErrorVoidApi
    public void update(@ErrorVoidApiParamter @PostApiIP PosterDto posterDto,MultipartFile file)  throws Exception {


        if (file == null) throw new Exception("请求参数缺失");

        if (file.isEmpty()) {
            throw new Exception("上传失败，请选择文件");
        }

        posterService.update(posterDto,file);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(("删除海报"))
    @ApiIdempotence
    @ErrorVoidApi
    public void delete(@PathVariable("id") Long id,@RequestParam("uuid") String uuid)  throws Exception {
        posterService.deleteById(id,uuid);
    }

    @DeleteMapping("")
    @ApiOperation(("删除所有海报"))
    @ApiIdempotence
    @ErrorVoidApi
    public void deleteAll(@RequestParam("uuid") String uuid)  throws Exception {
        posterService.deleteAll(uuid);
    }


    //===================================================================
    @GetMapping("")
    @ApiOperation("获取所有海报")
    public List<Poster> list(@RequestParam(name = "status",required = false) Boolean status) throws ClassNotFoundException {

        return posterService.findByMap(status);
    }

}
