package com.cinema.main_api.service;

import model.entity.Activity;

import java.util.List;

public interface ActivityService {

    void create(Activity activity,String uuid) throws Exception;

    Activity finfById(Long id) throws ClassNotFoundException;

    List<Activity> findAll() throws ClassNotFoundException;

    void deleteById(Long id,String uuid) throws Exception;
}
