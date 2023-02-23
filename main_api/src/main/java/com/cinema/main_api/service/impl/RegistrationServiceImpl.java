package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import api.write.WriteApi;
import com.cinema.main_api.service.RegistrationService;
import com.cinema.main_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.Registration;
import org.springframework.stereotype.Service;
import type.RedisTable;
import util.RDTCUitl;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Resource
    private ReadApi readApi;

    @Resource
    private WriteApi writeApi;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public void create(Registration registration,String uuid) throws Exception {

        writeApi.saveRegistration(registration,uuid);
    }

    @Override
    public Registration findById(Long id) {
        return null;
    }

    @Override
    public List<Registration> findAll() throws ClassNotFoundException {

        if (redisUtils.hSize(RedisTable.HASH.REGISTRATION) == 0) {

            log.info("查询数据库 ");
            List<Registration> registrations = readApi.findAllReadRegistration();
            for (Registration registration : registrations) {
                redisUtils.hPut(RedisTable.HASH.REGISTRATION,String.valueOf(registration.getId()),String.valueOf(registration));
            }

        }

        log.info("查询缓存");
        List<Object> objects = redisUtils.hValues(RedisTable.HASH.REGISTRATION);
        List<Registration> registrations = new ArrayList<>();

        for (Object object : objects) {
            Registration data = new RDTCUitl<Registration>().getData(object, RDTCUitl.ClassName.REGISTRATION);
            registrations.add(data);
        }


        return registrations;
    }

    @Override
    public void deleteById(Long id,String uuid) {

    }
}
