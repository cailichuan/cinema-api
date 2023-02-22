package com.cinema.read_api.service.impl;

import com.cinema.read_api.mapper.ReadUploadMapper;
import com.cinema.read_api.service.ReadUploadService;

import model.entity.Upload;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import type.Path;

import javax.annotation.Resource;
import java.io.File;

@Service
public class ReadUploadServiceImpl implements ReadUploadService {

    @Resource
    private ReadUploadMapper readUploadMapper;

    @Override
    public String getFileNameById(Long id) {
        Upload upload = readUploadMapper.selectById(id);
        if (upload!=null){
            //获取jar包所在的目录
            ApplicationHome homePath = new ApplicationHome(getClass());
            File jarF = homePath.getSource();

            //jar包目录下生成一个upload文件夹用来存储上传的图片
            String fileName = upload.getFileName(jarF.getParentFile().toString() + Path.IMAGES_PATH);

            return fileName;
        }
        return null;
    }


}
