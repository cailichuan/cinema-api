package com.cinema.write_api.service.impl;

import com.cinema.write_api.mapper.WriteRoleMapper;
import com.cinema.write_api.service.WriteRoleService;
import com.cinema.write_api.util.GetSnowId;
import com.cinema.write_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.Role;
import org.springframework.stereotype.Service;
import type.RedisTable;
import type.Roles;
import type.SnowIdType;
import util.DataTimeUtil;
import util.PatternUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class WriteRoleServiceImpl implements WriteRoleService {

    @Resource
    private WriteRoleMapper writeRoleMapper;

    @Resource
    private GetSnowId getSnowId;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public Role create(Role role) throws Exception {

        Map<String,Object> map = new HashMap<>();
        map.put("wid",role.getWid());
        map.put("value",role.getValue());
        if (writeRoleMapper.selectByMap(map).size()>0) {
            throw new Exception("该员工已拥有该权限，请不要重复添加");
        }

        getSnowId.setSonwIdType(SnowIdType.ROLE);
        long id = getSnowId.nextId();

        role.setId(id);
        role.setCreateAt(DataTimeUtil.getNowDateTimeString());
        writeRoleMapper.insert(role);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.ROLE,String.valueOf(role.getId()),String.valueOf(role));

        return role;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        Map<String,Long> map = new HashMap<>();
        map.put("id",id);

        List<Role> roles = writeRoleMapper.selectByMap(map);
        Role role = roles==null? null:roles.get(0);

        if (role.getValue().equals(Roles.ROLE_WORKER))
            throw new Exception("员工的基本权限不能删除");

        writeRoleMapper.deleteByMap(map);

        log.info("删除缓存");
        redisUtils.hDelete(RedisTable.HASH.ROLE,String.valueOf(id),String.valueOf(role));

    }

    @Override
    public void deleteWorkerAllRoles(Long wid) {

        Map<String,Long> map = new HashMap<>();
        map.put("wid",wid);

        writeRoleMapper.deleteByMap(map);

        log.info("删除缓存");
        //生成key
        String key = RedisTable.SET.WORKER_ID_BY_ROLE_ID+wid;

        Set<Object> objects = redisUtils.setMembers(key);
        List<Long> rids = PatternUtil.getStringNumLong(String.valueOf(objects));
        for (Long rid : rids) {
            redisUtils.hDelete(RedisTable.HASH.ROLE,String.valueOf(rid));
        }
        redisUtils.delete(key);

    }
}
