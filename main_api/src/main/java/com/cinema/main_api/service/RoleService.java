package com.cinema.main_api.service;

import model.entity.Role;

import java.util.List;

public interface RoleService {
    Role create(Role role) throws Exception;

    void deleteById(Integer id) throws Exception;

    List<Role> listRolesByWorkerId(Integer wid);

    void deleteWorkerAllRoles(Integer wid);
}
