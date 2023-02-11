package com.cinema.write_api.service.impl;

import com.cinema.write_api.mapper.WriteArrangementMapper;
import com.cinema.write_api.service.WriteArrangementService;
import com.cinema.write_api.util.GetSnowId;
import model.entity.Arrangement;
import type.SnowIdType;

import javax.annotation.Resource;

public class WriteArrangementServiceImpl implements WriteArrangementService {

    @Resource
    private GetSnowId snowId;
    @Resource
    private WriteArrangementMapper writeArrangementMapper;


    @Override
    public void save(Arrangement arrangement) {
        snowId.setSonwIdType(SnowIdType.Arrangement);
        Integer id = (int) snowId.nextId();
        arrangement.setId(id);
        writeArrangementMapper.insert(arrangement);
    }

    @Override
    public void deleteById(Integer id) {
        writeArrangementMapper.deleteById(id);
    }

    @Override
    public Arrangement Update(Arrangement arrangement) {

        writeArrangementMapper.updateById(arrangement);
        return arrangement;
    }
}
