package com.cinema.write_api.controller;

import annotation.*;
import com.cinema.write_api.service.WritePosterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.dto.PosterDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import util.RDTCUitl;

import javax.annotation.Resource;

@RestController
@Api(tags = "首页海报接口")
@RequestMapping("/writeapi/poster")
public class WritePosterController {

    @Resource
    private WritePosterService writePosterService;



    @PostMapping(value = "")
    @ApiOperation("添加首页海报")
    @PostApiIdempotence
    @ErrorVoidApi
    public void save(@ErrorVoidApiParamter @PostApiIP @RequestParam("posterDtoJson") String posterDtoJson, @RequestPart("file") MultipartFile file)  throws Exception {

        PosterDto posterDto = new RDTCUitl<PosterDto>().getData(posterDtoJson, RDTCUitl.ClassName.POSTERDTO);
        System.out.println("输出海报");

        System.out.println(posterDto.toPoster());



        if (file == null) throw new Exception("请求参数缺失");

        if (file.isEmpty()) {
            throw new Exception("上传失败，请选择文件");
        }





        writePosterService.save(posterDto.toPoster(), file);
    }

    @PutMapping("")
    @ApiOperation("更新海报")
    @PostApiIdempotence
    @ErrorVoidApi
    public void update(@ErrorVoidApiParamter @PostApiIP @RequestParam("posterDtoJson")String posterDtoJson,MultipartFile file)  throws Exception {


        if (file == null) throw new Exception("请求参数缺失");

        if (file.isEmpty()) {
            throw new Exception("上传失败，请选择文件");
        }

        PosterDto posterDto = new RDTCUitl<PosterDto>().getData(posterDtoJson, RDTCUitl.ClassName.POSTERDTO);
        writePosterService.update(posterDto.toPoster(), file);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(("删除海报"))
    @ApiIdempotence
    @ErrorVoidApi
    public void delete(@PathVariable("id") Long id,@RequestParam("uuid") String uuid)  throws Exception {
        writePosterService.deleteById(id);
    }

    @DeleteMapping("")
    @ApiOperation(("删除所有海报"))
    @ApiIdempotence
    @ErrorVoidApi
    public void deleteAll(@RequestParam("uuid") String uuid)  throws Exception {
        writePosterService.deleteAll();
    }
}
