package com.cinema.read_api.service.impl;

import com.cinema.read_api.mapper.ReadRoleMapper;
import com.cinema.read_api.service.ReadRoleService;
import model.entity.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReadRoleServiceImpl implements ReadRoleService {

    @Resource
    private ReadRoleMapper readRoleMapper;
    @Override
    public List<Role> listRolesByWorkerId(Long wid) {
        return readRoleMapper.selectListByWid(wid);
    }
}
