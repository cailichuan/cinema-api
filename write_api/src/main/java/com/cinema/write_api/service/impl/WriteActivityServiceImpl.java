package com.cinema.write_api.service.impl;


import com.cinema.write_api.mapper.WriteActivityMapper;
import com.cinema.write_api.service.WriteActivityService;
import com.cinema.write_api.util.GetSnowId;
import model.entity.Activity;
import org.springframework.stereotype.Service;
import type.SnowIdType;

import javax.annotation.Resource;

@Service
public class WriteActivityServiceImpl implements WriteActivityService {

    @Resource
    private GetSnowId snowId;

    @Resource
    private WriteActivityMapper writeActivityMapper;

    @Override
    public void create(Activity activity) {
        snowId.setSonwIdType(SnowIdType.Activity);
        Integer id = (int)snowId.nextId();
        activity.setId(id);

        writeActivityMapper.insert(activity);
    }

    @Override
    public void deleteById(Integer id) {

    }
}
