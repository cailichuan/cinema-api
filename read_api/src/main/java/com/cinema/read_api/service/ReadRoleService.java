package com.cinema.read_api.service;

import model.entity.Role;

import java.util.List;

public interface ReadRoleService {
    Role create(Role role) throws Exception;

    void deleteById(Integer id) throws Exception;

    List<Role> listRolesByWorkerId(Integer wid);

    void deleteWorkerAllRoles(Integer wid);
}
