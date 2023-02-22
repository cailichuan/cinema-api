package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import api.write.WriteApi;
import com.cinema.main_api.service.ActivityService;
import model.entity.Activity;
import model.support.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    public ReadApi readApi;

    @Resource
    public WriteApi writeApi;


    @Override
    public void create(Activity activity) {
            writeApi.createActivity(activity);
    }

    @Override
    public Activity finfById(Long id) {

       return readApi.findActivityById(id);

    }

    @Override
    public List<Activity> findAll() {


        return readApi.findAllActivity();
    }

    @Override
    public void deleteById(Long id) {

    }
}
