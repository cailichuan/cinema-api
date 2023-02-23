package com.cinema.write_api.service.impl;

import com.cinema.write_api.mapper.WriteActivityMapper;
import com.cinema.write_api.mapper.WriteRegistrationMapper;
import com.cinema.write_api.service.WriteRegistrationService;
import com.cinema.write_api.util.GetSnowId;
import com.cinema.write_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.Activity;
import model.entity.Registration;
import org.springframework.stereotype.Service;
import type.RedisTable;
import type.SnowIdType;
import util.DataTimeUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class WriteRegistrationServiceImpl implements WriteRegistrationService {

    @Resource
    private WriteRegistrationMapper writeRegistrationMapper;

    @Resource
    private WriteActivityMapper writeActivityMapper;

    @Resource
    private GetSnowId getSnowId;

    @Resource
    private RedisUtils redisUtils;


    @Override
    public void create(Registration registration) throws Exception {

        //获取要参加的活动id
        Long aid = registration.getAid();
        Map<String,Long> map = new HashMap<>();
        map.put("id",aid);
        List<Activity> activities =writeActivityMapper.selectByMap(map);
        if (activities.size()==0)throw new Exception("请求参数错误");
        Activity activity = activities.get(0);

        //查看用户是否已经参加
        Map<String,Long> map1 = new HashMap<>();
        map1.put("aid",aid);
        map1.put("uid",registration.getUid());
        if (writeRegistrationMapper.selectByMap(map1).size()>0){
            throw new Exception("您已经参加过活动");
        }

        //判断活动是否为开始
        if(DataTimeUtil.dateTimeIsAfterNow(activity.getStartTime())) {
            throw new Exception("活动没有开始");
        }

        //判断时候结束
        if (!DataTimeUtil.dateTimeIsAfterNow(activity.getEndTime())) {
            throw new Exception("活动已经结束了");
        }
        activity.setNumber(activity.getNumber()+1);
        writeActivityMapper.update(activity);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.ACTIVITY,String.valueOf(activity.getId()),String.valueOf(activity));


        getSnowId.setSonwIdType(SnowIdType.RESGISTRATION);
        long id = getSnowId.nextId();

        registration.setId(id);
        registration.setCreateAt(DataTimeUtil.getNowDateTimeString());
        writeRegistrationMapper.insert(registration);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.REGISTRATION,String.valueOf(id),String.valueOf(registration));


    }

    @Override
    public void deleteById(Long id) {

        Map<String,Long> map = new HashMap<>();
        map.put("id",id);

        writeRegistrationMapper.deleteByMap(map);

        log.info("删除缓存");
        redisUtils.hDelete(RedisTable.HASH.REGISTRATION,String.valueOf(id));

    }
}
