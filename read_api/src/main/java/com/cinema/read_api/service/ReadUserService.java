package com.cinema.read_api.service;


import model.dto.LoginDto;
import model.entity.User;

import java.util.List;

public interface ReadUserService {

    User login(LoginDto dto) throws Exception;

    List<User> findAll();

    User findById(Integer id);

    User update(User user);

    User save(User user) throws Exception;

    User findByUsername(String username);

    void deleteById(Integer id);

}
