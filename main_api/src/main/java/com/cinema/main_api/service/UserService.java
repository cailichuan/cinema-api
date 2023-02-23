package com.cinema.main_api.service;


import model.dto.LoginDto;
import model.dto.RegisterDto;
import model.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    User login(LoginDto dto) throws Exception;

    List<User> findAll() throws ClassNotFoundException;

    User findById(Long id) throws ClassNotFoundException;

    User update(User user,String uuid);

    User save(RegisterDto registerDto, MultipartFile Hs,String uuid) throws Exception;

    User findByUsername(String username);

    void deleteById(Long id,String uuid);

}
