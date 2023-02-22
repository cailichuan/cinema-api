package com.cinema.main_api.controller;


import annotation.DisableBaseResponse;
import com.cinema.main_api.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


/**
 * 上传图片令存放为二进制到mysql
 */
@RestController
@Api(tags = "上传接口")
@RequestMapping("/api/upload")
public class UploadController {

    @Resource
    private UploadService uploadService;




    @PostMapping("")
    @ApiOperation(value = "上传图片")
    public Integer upload(MultipartFile file,@RequestBody String path) throws Exception{
        if (file == null) {
            throw new Exception("请求参数缺失");
        }

        if (file.isEmpty()) {
            throw new Exception("上传失败，请选择文件");

        }

        return uploadService.checkAndSaveUpload(file,path);
    }

    @GetMapping("")
    @ApiOperation(value = "获取图片")
    public String get(@RequestParam(name = "id") Long id){
        if (id == null) {
            return null;
        }

        return  uploadService.getFileNameById(id);

    }
}
