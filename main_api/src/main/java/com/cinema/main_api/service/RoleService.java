package com.cinema.main_api.service;

import model.entity.Role;

import java.util.List;

public interface RoleService {
    Role create(Role role,String uuid) throws Exception;

    void deleteById(Long id,String uuid) throws Exception;

    List<Role> listRolesByWorkerId(Long wid) throws ClassNotFoundException;

    void deleteWorkerAllRoles(Long wid,String uuid);
}
