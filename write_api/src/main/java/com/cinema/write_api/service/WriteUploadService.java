package com.cinema.write_api.service;

import model.dto.UploadDto;
import model.entity.Upload;
import model.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface WriteUploadService {


    //检验文件并且保存 返回值为Upload字段的id
    Long checkAndSaveUpload(MultipartFile file,String path)throws Exception;

    void deleteById(Long id) throws Exception;


    //把图片数据存入数据库
    Upload sava(Upload upload);



    //把图片存入服务器中
    void savaUploadInFile(UploadDto uploadDto) throws Exception;



}
