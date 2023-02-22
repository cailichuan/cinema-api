package com.cinema.write_api.controller;

import annotation.DisableBaseResponse;
import com.cinema.write_api.service.WriteUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 上传图片存放为二进制到mysql
 */

@RestController
@Api(tags = "上传接口")
@RequestMapping("/writeapi/upload")
public class WriteUploadController {

    @Resource
    private WriteUploadService writeUploadService;

    @PostMapping("")
    @ApiOperation(value = "上传图片")
    public Long upload(MultipartFile file,@RequestParam(name = "path") String path ) throws Exception{

        System.out.println("请求已经进来了");
        if (file == null) throw new Exception("请求参数缺失");
        if (file.isEmpty()) {
            throw new Exception("上传失败，请选择文件");
        }

        if (path==null || path.equals("")){
            throw new Exception("路径名不能为空");
        }



        System.out.println("请求将要处理");

        return writeUploadService.checkAndSaveUpload(file,path);
    }




}
