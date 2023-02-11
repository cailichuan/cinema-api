package com.cinema.read_api.service;

import model.entity.OrderException;

import java.util.List;

public interface ReadOrderExceptionService {

    OrderException create(OrderException orderException);

    List<OrderException> findAll();

    void handleException(OrderException orderException);
}
