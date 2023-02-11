package com.cinema.main_api.service.impl;


import com.cinema.main_api.service.UploadService;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;



@Service
public class UploadServiceImpl implements UploadService {

    @Override
    public String checkAndSaveUpload(MultipartFile file) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
