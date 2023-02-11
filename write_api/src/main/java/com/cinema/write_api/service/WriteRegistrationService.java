package com.cinema.write_api.service;

import model.entity.Registration;

import java.util.List;

public interface WriteRegistrationService {
    void create(Registration registration) throws Exception;



    void deleteById(Integer id);
}
