package com.cinema.read_api.service.impl;

import com.cinema.read_api.mapper.ReadRoleMapper;
import com.cinema.read_api.service.ReadRoleService;
import model.entity.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReadRoleServiceImpl implements ReadRoleService {

    @Resource
    private ReadRoleMapper readRoleMapper;
    @Override
    public List<Role> listRolesByWorkerId(Long wid) {
        Map<String,Long> map = new HashMap<>();
        map.put("wid",wid);
        return readRoleMapper.selectByMap(map);
    }


    @Override
    public Role findById(Long id) {
        Map<String,Long> map = new HashMap<>();
        map.put("id",id);
        List<Role> roles = readRoleMapper.selectByMap(map);
        return roles==null? null:roles.get(0);
    }
}
