package com.cinema.read_api.service;

import model.entity.Registration;

import java.util.List;

public interface ReadRegistrationService {
    void create(Registration registration) throws Exception;

    Registration findById(Integer id);

    List<Registration> findAll();

    void deleteById(Integer id);
}