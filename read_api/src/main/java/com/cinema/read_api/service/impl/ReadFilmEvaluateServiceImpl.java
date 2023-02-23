package com.cinema.read_api.service.impl;

import com.cinema.read_api.mapper.ReadFilmEvaluateMapper;
import com.cinema.read_api.mapper.ReadUserMapper;
import com.cinema.read_api.service.ReadFilmEvaluateService;
import model.entity.FilmEvaluate;
import model.vo.FilmEvaluateVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReadFilmEvaluateServiceImpl implements ReadFilmEvaluateService {

    @Resource
    private ReadFilmEvaluateMapper readFilmEvaluateMapper;

    @Resource
    private ReadUserMapper readUserMapper;
    @Override
    public List<FilmEvaluateVo> findAllByFilmId(Long fid) {
        List<FilmEvaluateVo> result = new ArrayList<>();

        Map<String,Long> map = new HashMap<>();
        map.put("fid",fid);

        List<FilmEvaluate> filmEvaluates = readFilmEvaluateMapper.selectListByMap(map);
        for (FilmEvaluate filmEvaluate : filmEvaluates) {
            FilmEvaluateVo filmEvaluateVo = new FilmEvaluateVo(filmEvaluate.getId(),filmEvaluate,readUserMapper.selectById(filmEvaluate.getUid()));


            result.add(filmEvaluateVo);
        }
        return result;
    }

    @Override
    public FilmEvaluate findById(Long id) {

        Map<String,Long> map = new HashMap<>();
        map.put("id",id);

        List<FilmEvaluate> filmEvaluates = readFilmEvaluateMapper.selectListByMap(map);

        return filmEvaluates==null? null:filmEvaluates.get(0);
    }
}
