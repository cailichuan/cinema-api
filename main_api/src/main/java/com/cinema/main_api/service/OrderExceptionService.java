package com.cinema.main_api.service;

import model.entity.OrderException;

import java.util.List;

public interface OrderExceptionService {

    OrderException create(OrderException orderException);

    List<OrderException> findAll();

    void handleException(OrderException orderException);
}
