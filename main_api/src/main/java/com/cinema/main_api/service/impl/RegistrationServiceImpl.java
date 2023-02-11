package com.cinema.main_api.service.impl;


import com.cinema.main_api.service.RegistrationService;
import model.entity.Registration;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    public RegistrationServiceImpl() {
        super();
    }

    @Override
    public void create(Registration registration) throws Exception {

    }

    @Override
    public Registration findById(Integer id) {
        return null;
    }

    @Override
    public List<Registration> findAll() {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
