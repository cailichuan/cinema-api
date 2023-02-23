package com.cinema.write_api.service.impl;

import com.cinema.write_api.mapper.WriteRoleMapper;
import com.cinema.write_api.mapper.WriteWorkerMapper;
import com.cinema.write_api.service.WriteRoleService;
import com.cinema.write_api.service.WriteWorkerService;
import com.cinema.write_api.util.GetSnowId;
import com.cinema.write_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.Role;
import model.entity.Worker;
import org.springframework.stereotype.Service;
import type.RedisTable;
import type.Roles;
import type.SnowIdType;
import util.DataTimeUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class WriteWorkerServiceImpl implements WriteWorkerService {

    @Resource
    private WriteWorkerMapper writeWorkerMapper;

    @Resource
    private WriteRoleMapper writeRoleMapper;

    @Resource
    private WriteRoleService writeRoleService;

   @Resource
   private GetSnowId getSnowId;

   @Resource
   private RedisUtils redisUtils;


    @Override
    public Worker create(Worker worker) throws Exception {
        Map<String,Long> map = new HashMap<>();
        map.put("id",worker.getId());

        if (writeWorkerMapper.selectByMap(map).size()>0) {
            throw new Exception("用户名已经存在");
        }

        getSnowId.setSonwIdType(SnowIdType.WORKER);
        long id = getSnowId.nextId();

        String now= DataTimeUtil.getNowDateTimeString();
        worker.setEntry(true);
        worker.setPassword(worker.getPassword());
        worker.setId(id);
        worker.setCreateAt(now);
        worker.setUpdateAt(now);
        writeWorkerMapper.insert(worker);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.WORKER,String.valueOf(id),String.valueOf(worker));

        //添加worker的权限
        getSnowId.setSonwIdType(SnowIdType.ROLE);
        long rid = getSnowId.nextId();
        Role role = new Role(rid, worker.getId(), Roles.ROLE_WORKER, now);
        writeRoleMapper.insert(role);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.ROLE,String.valueOf(rid),String.valueOf(role));

        return worker;
    }

    @Override
    public void update(Worker worker) throws Exception {

        Map<String,String> map = new HashMap<>();
        map.put("username",worker.getUsername());

        if (writeWorkerMapper.selectByMap(map).size()>0) {
            throw new Exception("用户名已经存在");
        }

        worker.setUpdateAt(DataTimeUtil.getNowDateTimeString());

        writeWorkerMapper.update(worker);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.WORKER,String.valueOf(worker.getId()),String.valueOf(worker));


    }

    @Override
    public void deleteById(Long id) {

        Map<String,Long> map = new HashMap<>();
        map.put("id",id);
        writeWorkerMapper.deleteByMap(map);

        log.info("删除缓存");
        redisUtils.hDelete(RedisTable.HASH.WORKER,String.valueOf(id));

        //删除所有权限
        writeRoleService.deleteWorkerAllRoles(id);

    }
}
