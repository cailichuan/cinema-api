package com.cinema.write_api.service;

import model.entity.Activity;

import java.util.List;

public interface WriteActivityService {

    void create(Activity activity);



    void deleteById(Long id);
}
