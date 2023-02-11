package com.cinema.main_api.service.impl;


import com.cinema.main_api.service.OrderExceptionService;
import model.entity.OrderException;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class OrderExceptionServiceImpl implements OrderExceptionService {

    public OrderExceptionServiceImpl() {
        super();
    }

    @Override
    public OrderException create(OrderException orderException) {
        return null;
    }

    @Override
    public List<OrderException> findAll() {
        return null;
    }

    @Override
    public void handleException(OrderException orderException) {

    }
}
