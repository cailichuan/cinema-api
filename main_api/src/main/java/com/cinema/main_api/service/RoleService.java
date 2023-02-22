package com.cinema.main_api.service;

import model.entity.Role;

import java.util.List;

public interface RoleService {
    Role create(Role role) throws Exception;

    void deleteById(Long id) throws Exception;

    List<Role> listRolesByWorkerId(Long wid);

    void deleteWorkerAllRoles(Long wid);
}
