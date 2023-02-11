package com.cinema.read_api.service;

import model.entity.Activity;


import java.util.List;

public interface ReadActivityService{

    Activity finfById(Integer id);


    List<Activity> findAll();
}
