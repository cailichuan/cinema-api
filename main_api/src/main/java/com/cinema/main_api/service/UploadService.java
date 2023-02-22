package com.cinema.main_api.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {


    //检验文件并且保存 返回值为Upload字段的id
    Integer checkAndSaveUpload(MultipartFile file,String path);

    void deleteById(Long id);


    String getFileNameById(Long id);
}
