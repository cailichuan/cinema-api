package com.cinema.read_api.service.impl;

import com.cinema.read_api.mapper.ReadUserMapper;
import com.cinema.read_api.service.ReadUserService;
import model.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReadUserServiceImpl implements ReadUserService {

    @Resource
    private ReadUserMapper readUserMapper;

    @Override
    public User findByUserName(String userName) {

        return readUserMapper.selectByUserName(userName);
    }


    @Override
    public List<User> findAll() {
        return readUserMapper.selectList();
    }

    @Override
    public User findById(Long id) {
        return readUserMapper.selectById(id);
    }
}
