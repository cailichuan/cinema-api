package com.cinema.read_api.service;

import model.entity.Activity;


import java.util.List;

public interface ReadActivityService{

    Activity finfById(Long id);


    List<Activity> findAll();
}
