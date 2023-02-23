package com.cinema.read_api.controller;

import annotation.DisableBaseResponse;
import com.cinema.read_api.service.ReadUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Upload;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * 上传图片
 */
@RestController
@Api(tags = "上传接口")
@RequestMapping("/readapi/upload")
public class ReadUploadController {

    @Resource
    private ReadUploadService readUploadService;

    @GetMapping(value = "",produces = MediaType.IMAGE_JPEG_VALUE)
    @ApiOperation(value = "获取图片")
    @DisableBaseResponse
    public byte[] get(@RequestParam(name = "id") Long id) throws IOException {


        return id==null? null:readUploadService.getFileById(id);

    }

}
