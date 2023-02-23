package com.cinema.read_api.service;

import model.entity.Poster;

import java.util.List;
import java.util.Map;

public interface ReadPosterService {


    List<Poster> findByMap(Map map);



}
