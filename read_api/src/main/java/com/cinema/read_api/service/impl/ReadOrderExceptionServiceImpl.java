package com.cinema.read_api.service.impl;

import com.cinema.read_api.mapper.ReadOrderExceptionMapper;
import com.cinema.read_api.service.ReadOrderExceptionService;
import com.cinema.read_api.service.ReadOrderService;
import model.entity.OrderException;
import model.vo.OrderVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReadOrderExceptionServiceImpl implements ReadOrderExceptionService {

    @Resource
    private ReadOrderExceptionMapper readOrderExceptionMapper;


    @Override
    public List<OrderException> findAll() {
        return readOrderExceptionMapper.selectList();
    }
}
