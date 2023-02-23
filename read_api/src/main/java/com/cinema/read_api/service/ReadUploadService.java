package com.cinema.read_api.service;

import model.entity.Upload;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ReadUploadService {


    byte[] getFileById(Long id) throws IOException;


}
