package com.cinema.main_api.service.impl;


import com.cinema.main_api.service.AdminService;
import model.dto.LoginDto;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {


    public AdminServiceImpl() {
        super();
    }

    @Override
    public String login(LoginDto loginDto) {
        return null;
    }
}
