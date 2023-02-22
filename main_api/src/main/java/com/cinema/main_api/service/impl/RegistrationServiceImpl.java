package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import com.cinema.main_api.service.RegistrationService;
import model.entity.Registration;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Resource
    private ReadApi readApi;

    @Override
    public void create(Registration registration) throws Exception {

    }

    @Override
    public Registration findById(Integer id) {
        return null;
    }

    @Override
    public List<Registration> findAll() {
        return readApi.findAllReadRegistration();
    }

    @Override
    public void deleteById(Integer id) {

    }
}
