package com.cinema.main_api.service;

import model.entity.Activity;

import java.util.List;

public interface ActivityService {

    void create(Activity activity);

    Activity finfById(Integer id);

    List<Activity> findAll();

    void deleteById(Integer id);
}
