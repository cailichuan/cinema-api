package com.cinema.write_api.service;

import model.entity.OrderException;

import java.util.List;

public interface WriteOrderExceptionService {

    OrderException create(OrderException orderException);



    void handleException(OrderException orderException);
}
