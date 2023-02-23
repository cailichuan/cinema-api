package com.cinema.read_api.service.impl;

import com.cinema.read_api.mapper.ReadUploadMapper;
import com.cinema.read_api.service.ReadUploadService;

import model.entity.Upload;

import org.springframework.stereotype.Service;

import util.PathUtil;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class ReadUploadServiceImpl implements ReadUploadService {

    @Resource
    private ReadUploadMapper readUploadMapper;

    @Override
    public byte[] getFileById(Long id) throws IOException {
        System.out.println(id);
        Upload upload = readUploadMapper.selectById(id);
        if (upload!=null){
            String fileName = PathUtil.getCommonsImgPath()+"\\"+upload.getFileName();
            File file = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes= new byte[fileInputStream.available()];
            fileInputStream.read(bytes,0,fileInputStream.available());


            return bytes;
        }
        return null;
    }


}
