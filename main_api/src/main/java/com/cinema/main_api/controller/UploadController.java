package com.cinema.main_api.controller;


import annotation.ApiIdempotence;
import annotation.DisableBaseResponse;
import annotation.ErrorVoidApi;
import com.cinema.main_api.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;


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
    @ApiIdempotence
    public Long upload(@RequestPart("file")MultipartFile file,@RequestParam("path") String path,@RequestParam(name = "uuid") String uuid) throws Exception{
        if (file == null) {
            throw new Exception("请求参数缺失");
        }

        if (file.isEmpty()) {
            throw new Exception("上传失败，请选择文件");

        }

        return uploadService.checkAndSaveUpload(file,path,uuid);
    }


    @DeleteMapping("")
    @ApiOperation(value = "删除图片")
    @ApiIdempotence
    @ErrorVoidApi
    public void delete(@RequestParam("id") Long id,@RequestParam("uuid") String uuid)  throws Exception {
        uploadService.deleteById(id,uuid);
    }

    //===================================================
    @GetMapping(value = "",produces = MediaType.IMAGE_JPEG_VALUE)
    @ApiOperation(value = "获取图片")
    @DisableBaseResponse
    public byte[] get(@RequestParam(name = "id") Long id) throws IOException {
        if (id == null) {
            return null;
        }

        return  uploadService.getFileById(id);

    }
}
