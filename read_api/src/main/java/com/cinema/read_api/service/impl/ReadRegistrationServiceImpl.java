package com.cinema.read_api.service.impl;

import com.cinema.read_api.mapper.ReadRegistrationMapper;
import com.cinema.read_api.service.ReadRegistrationService;
import model.entity.Registration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReadRegistrationServiceImpl implements ReadRegistrationService {

    @Resource
    private ReadRegistrationMapper readRegistrationMapper;


    @Override
    public List<Registration> findAll() {
        return readRegistrationMapper.selectList();
    }
}
