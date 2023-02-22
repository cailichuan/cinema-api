package com.cinema.read_api.controller;

import annotation.DisableBaseResponse;
import com.cinema.read_api.service.ReadUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Upload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletResponse;


/**
 * 上传图片
 */
@RestController
@Api(tags = "上传接口")
@RequestMapping("/readapi/upload")
public class ReadUploadController {

    @Resource
    private ReadUploadService readUploadService;

    @GetMapping("")
    @ApiOperation(value = "获取图片")
    @DisableBaseResponse
    public String get(@RequestParam(name = "id") Long id){


        return id==null? null:readUploadService.getFileNameById(id);

    }

}
