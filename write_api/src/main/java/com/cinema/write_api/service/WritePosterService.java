package com.cinema.write_api.service;

import model.entity.Poster;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

public interface WritePosterService {
    void save(Poster poster,MultipartFile file) throws Exception;

    void update(Poster poster, MultipartFile file) throws Exception;



    void deleteById(Long id) throws Exception;

    void deleteAll() throws Exception;
}
