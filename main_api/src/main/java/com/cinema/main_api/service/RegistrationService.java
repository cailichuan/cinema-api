package com.cinema.main_api.service;

import model.entity.Registration;

import java.util.List;

public interface RegistrationService {
    void create(Registration registration,String uuid) throws Exception;

    Registration findById(Long id);

    List<Registration> findAll() throws ClassNotFoundException;

    void deleteById(Long id,String uuid);
}
