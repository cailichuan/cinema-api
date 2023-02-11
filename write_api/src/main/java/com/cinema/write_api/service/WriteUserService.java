package com.cinema.write_api.service;


import model.dto.LoginDto;
import model.entity.User;

import java.util.List;

public interface WriteUserService {







    User update(User user);

    User save(User user) throws Exception;


    void deleteById(Integer id);

}
