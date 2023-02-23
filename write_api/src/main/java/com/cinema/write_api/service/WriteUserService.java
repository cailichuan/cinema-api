package com.cinema.write_api.service;


import model.dto.LoginDto;
import model.dto.RegisterDto;
import model.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WriteUserService {







    User update(User user);

    User save(RegisterDto registerDto,MultipartFile Hs) ;


    void deleteById(Long id);

}
