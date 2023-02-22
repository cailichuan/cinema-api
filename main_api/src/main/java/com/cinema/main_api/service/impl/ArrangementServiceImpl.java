package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import api.write.WriteApi;
import com.cinema.main_api.service.ArrangementService;
import model.entity.Arrangement;
import model.vo.ArrangementVo;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ArrangementServiceImpl implements ArrangementService {

    @Resource
    private WriteApi writeApi;

    @Resource
    private ReadApi readApi;

    public ArrangementServiceImpl() {
        super();
    }

    @Override
    public void save(Arrangement arrangement) {
        writeApi.createArrangement(arrangement);
    }

    @Override
    public List<Arrangement> findAll() {
        return readApi.findAllArrangement();
    }

    @Override
    public ArrangementVo findByFilmId(Long fid) {
        return readApi.findArrangemenByFilmId(fid);
    }

    @Override
    public List<Integer> getSeatsHaveSelected(Long id) {
        return readApi.getArrangementSeats(id);
    }

    @Override
    public Map<String,Object> findById(Long id) {
        return readApi.findArrangementById(id);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Arrangement Update(Arrangement arrangement) {
        return null;
    }
}
