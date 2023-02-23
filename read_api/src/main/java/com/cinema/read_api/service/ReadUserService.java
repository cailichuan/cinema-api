package com.cinema.read_api.service;


import model.dto.LoginDto;
import model.entity.User;

import java.util.List;

public interface ReadUserService {



    User findByUserName(String userName);

    User findById(Long id);


    List<User> findAll();



}
