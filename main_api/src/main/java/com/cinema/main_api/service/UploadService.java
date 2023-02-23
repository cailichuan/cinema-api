package com.cinema.main_api.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {


    //检验文件并且保存 返回值为Upload字段的id
    Long checkAndSaveUpload(MultipartFile file,String path,String uuid) throws Exception;

    void deleteById(Long id,String uuid) throws Exception;


    byte[] getFileById(Long id) throws IOException;
}
