package com.cinema.read_api.service;

import model.entity.Role;

import java.util.List;

public interface ReadRoleService {


    List<Role> listRolesByWorkerId(Long wid);


}
