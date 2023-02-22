package com.cinema.read_api.service;

import model.entity.Upload;
import org.springframework.web.multipart.MultipartFile;

public interface ReadUploadService {


    String getFileNameById(Long id);


}
