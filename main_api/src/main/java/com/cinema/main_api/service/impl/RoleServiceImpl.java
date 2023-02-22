package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import com.cinema.main_api.service.RoleService;
import model.entity.Role;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "role")
public class RoleServiceImpl implements RoleService {

    @Resource
    private ReadApi readApi;

    @Override
    public Role create(Role role) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) throws Exception {

    }

    @Override
    public List<Role> listRolesByWorkerId(Long wid) {
        return readApi.findRoleByWid(wid);
    }

    @Override
    public void deleteWorkerAllRoles(Long wid) {

    }
}
