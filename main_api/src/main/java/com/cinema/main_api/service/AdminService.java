package com.cinema.main_api.service;

import model.dto.LoginDto;

public interface AdminService {

    String login(LoginDto loginDto);
}
