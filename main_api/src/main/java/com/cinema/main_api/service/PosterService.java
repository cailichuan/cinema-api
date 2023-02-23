package com.cinema.main_api.service;

import model.dto.PosterDto;
import model.entity.Poster;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PosterService {
    void save(PosterDto posterDto,MultipartFile file) throws Exception;

    void update(PosterDto posterDto,MultipartFile file) throws Exception;


    List<Poster> findByMap(Boolean status) throws ClassNotFoundException;

    void deleteById(Long id,String uuid) throws Exception;

    void deleteAll(String uuid) throws Exception;
}
