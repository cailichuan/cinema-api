package com.cinema.main_api.service.impl;

import cn.hutool.core.lang.UUID;
import com.cinema.main_api.service.UuidService;
import com.cinema.main_api.util.RedisUtils;
import org.springframework.stereotype.Service;
import type.RedisTable;

import javax.annotation.Resource;

@Service
public class UuidServiceImpl implements UuidService {

    @Resource
    private RedisUtils redisUtils;


    @Override
    public String getUUid() {

        String uuid = UUID.randomUUID().toString();

        redisUtils.hPut(RedisTable.HASH.WRITE_CHARACTERISTIC,uuid,uuid);


        return uuid;
    }
}
