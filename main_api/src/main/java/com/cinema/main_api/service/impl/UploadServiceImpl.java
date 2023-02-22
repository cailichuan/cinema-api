package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import api.write.WriteApi;
import com.cinema.main_api.service.UploadService;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


@Service
public class UploadServiceImpl implements UploadService {

    @Resource
    private WriteApi writeApi;

    @Resource
    private ReadApi readApi;

    @Override
    public Integer checkAndSaveUpload(MultipartFile file,String path) {
        return writeApi.savaUpload(file,path);
    }


    @Override
    public String getFileNameById(Long id) {
        return readApi.findUploadFileNameById(id);
    }

    @Override
    public void deleteById(Long id) {

    }
}
