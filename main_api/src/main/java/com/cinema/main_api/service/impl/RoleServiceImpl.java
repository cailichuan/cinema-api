package com.cinema.main_api.service.impl;


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

    public RoleServiceImpl() {
        super();
    }

    @Override
    public Role create(Role role) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Integer id) throws Exception {

    }

    @Override
    public List<Role> listRolesByWorkerId(Integer wid) {
        return null;
    }

    @Override
    public void deleteWorkerAllRoles(Integer wid) {

    }
}
