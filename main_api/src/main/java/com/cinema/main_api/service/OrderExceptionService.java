package com.cinema.main_api.service;

import model.entity.OrderException;

import java.util.List;

public interface OrderExceptionService {

    OrderException create(OrderException orderException,String uuid) throws Exception;

    List<OrderException> findAll() throws ClassNotFoundException;

    void handleException(OrderException orderException,String uid) throws Exception;
}
