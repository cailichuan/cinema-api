package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import api.write.WriteApi;
import com.cinema.main_api.service.RoleService;
import com.cinema.main_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.Role;
import model.entity.Worker;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import type.RedisTable;
import util.PatternUtil;
import util.RDTCUitl;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Resource
    private ReadApi readApi;

    @Resource
    private WriteApi writeApi;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public Role create(Role role,String uuid) throws Exception {

        writeApi.saveRole(role,uuid);
        return role;
    }

    @Override
    public void deleteById(Long id,String uuid) throws Exception {

        writeApi.deleteRole(id,uuid);
    }

    @Override
    public List<Role> listRolesByWorkerId(Long wid) throws ClassNotFoundException {
        //生成key
        String key= RedisTable.SET.WORKER_ID_BY_ROLE_ID+wid;

        if (redisUtils.sSize(key)==0) {

            log.info("查询数据库");
            List<Role> roles = readApi.findRoleByWid(wid);

            //写入缓存
            for (Role role : roles) {
                redisUtils.sAdd(key,String.valueOf(role.getId()));
                redisUtils.hPutIfAbsent(RedisTable.HASH.ROLE,String.valueOf(role.getId()),String.valueOf(role));
            }

            return roles;
        }


        log.info("查询缓存");
        List<Role> roles=new ArrayList<>();
        Set<Object> objects = redisUtils.setMembers(key);
        List<Long> rids = PatternUtil.getStringNumLong(String.valueOf(objects));

        for (Long rid : rids) {

            Object RO = redisUtils.hGet(RedisTable.HASH.ROLE, String.valueOf(rid));
            Role role=null;
            if (RO==null) {
                role = readApi.findRoleById(rid);
                if (role==null){
                    redisUtils.sRemove(key,String.valueOf(rid));
                    continue;
                }

                redisUtils.hPut(RedisTable.HASH.ROLE,String.valueOf(rid),String.valueOf(role));
            }

            else role= new RDTCUitl<Role>().getData(RO,RDTCUitl.ClassName.ROLE);

            if (role.getWid()!=wid){
                redisUtils.sRemove(key,String.valueOf(rid));
                continue;
            }

            roles.add(role);
        }


        return roles;
    }

    @Override
    public void deleteWorkerAllRoles(Long wid,String uuid) {


    }
}
