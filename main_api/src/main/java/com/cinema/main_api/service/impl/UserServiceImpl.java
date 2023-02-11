package com.cinema.main_api.service.impl;


import com.cinema.main_api.service.UserService;
import model.dto.LoginDto;
import model.entity.User;

import org.springframework.stereotype.Service;



import java.util.List;


@Service
public class UserServiceImpl implements UserService {


    public UserServiceImpl() {
        super();
    }

    @Override
    public User login(LoginDto dto) throws Exception {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User save(User user) throws Exception {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
