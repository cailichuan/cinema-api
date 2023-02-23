package com.cinema.main_api.service.impl;


import com.cinema.main_api.config.RabbitMqConfig;
import com.cinema.main_api.service.AdminService;
import model.dto.LoginDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {


    @Resource
    private RabbitTemplate rabbitTemplate;


    @Override
    public String login(LoginDto loginDto) {
        return null;
    }

    @Override
    public String test(Long id) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.ORDER_EXCHANGE,RabbitMqConfig.ODER_ROUTE_KEY,id);
        return "ok";
    }
}
