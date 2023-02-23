package com.cinema.write_api.service;

import model.entity.Role;

import java.util.List;

public interface WriteRoleService {
    Role create(Role role) throws Exception;

    void deleteById(Long id) throws Exception;



    void deleteWorkerAllRoles(Long wid);
}
