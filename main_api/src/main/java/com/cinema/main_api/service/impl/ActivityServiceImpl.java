package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import api.write.WriteApi;
import cn.hutool.json.JSONUtil;
import com.cinema.main_api.service.ActivityService;
import com.cinema.main_api.service.UuidService;
import com.cinema.main_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.Activity;

import org.springframework.stereotype.Service;
import type.RedisTable;
import util.RDTCUitl;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;



@Slf4j
@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ReadApi readApi;

   @Resource
    private WriteApi writeApi;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private UuidService uuidService;


    @Override
 public void create(Activity activity,String uuid) throws Exception {
            writeApi.createActivity(activity,uuidService.getUUid());
    }

    @Override
    public Activity finfById(Long id) throws ClassNotFoundException {


        //如果缓存中不存在数据，查询数据
        if (!redisUtils.hExists(RedisTable.HASH.ACTIVITY,String.valueOf(id))){

            log.info("查询mysql数据库");
            Activity activity = readApi.findActivityById(id);

            //不为空则存入缓存
            if (activity!=null) {
                redisUtils.hPut(RedisTable.HASH.ACTIVITY,String.valueOf(id),String.valueOf(activity));

            }
            return activity;
        }



        log.info("查询redis");
        Object o = redisUtils.hGet(RedisTable.HASH.ACTIVITY, String.valueOf(id));

         return new RDTCUitl<Activity>().getData(o,RDTCUitl.ClassName.ACTIVITY);


    }

    @Override
    public List<Activity> findAll() throws ClassNotFoundException {

        //判断redis缓存中是否有数据
        if (redisUtils.hSize(RedisTable.HASH.ACTIVITY) == 0) {
            //无数据则查询数据库
            log.info("查询数据库");
            List<Activity> allActivity = readApi.findAllActivity();

            System.out.println(allActivity);
            log.info("写入缓存");
            System.out.println(allActivity.get(0));


            //将数据存入缓存
            for (Activity activity : allActivity) {

                redisUtils.hPut(RedisTable.HASH.ACTIVITY,String.valueOf(activity.getId()),String.valueOf(activity));

            }


            log.info("缓存写入完成");

            return allActivity;
        }



        log.info("查询缓存");
        List<Activity> activities = new ArrayList<>();
        List<Object> objects = redisUtils.hValues(RedisTable.HASH.ACTIVITY);


        for (Object object : objects) {


            Activity data = new RDTCUitl<Activity>().getData(object,RDTCUitl.ClassName.ACTIVITY);

            activities.add(data);

        }

        return activities;


    }

    @Override
    public void deleteById(Long id ,String uuid) throws Exception {

        writeApi.deleteActivityById(id,uuid);
    }
}
