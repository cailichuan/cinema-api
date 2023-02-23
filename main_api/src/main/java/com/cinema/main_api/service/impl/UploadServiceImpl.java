package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import api.write.WriteApi;
import com.cinema.main_api.service.UploadService;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;


@Service
public class UploadServiceImpl implements UploadService {


    @Resource
    private ReadApi readApi;

    @Resource
    private WriteApi writeApi;

    @Override
    public Long checkAndSaveUpload(MultipartFile file,String path,String uuid) throws Exception {




        return writeApi.savaUpload(file,path,uuid);
    }


    @Override
    public byte[] getFileById(Long id) throws IOException {
        return readApi.findUploadFileById(id);
    }

    @Override
    public void deleteById(Long id,String uuid) throws Exception {

        writeApi.deleteUpload(id,uuid);
    }
}
