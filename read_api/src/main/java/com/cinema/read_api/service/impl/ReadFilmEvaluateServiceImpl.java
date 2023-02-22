package com.cinema.read_api.service.impl;

import com.cinema.read_api.mapper.ReadFilmEvaluateMapper;
import com.cinema.read_api.mapper.ReadUserMapper;
import com.cinema.read_api.service.ReadFilmEvaluateService;
import model.entity.FilmEvaluate;
import model.vo.FilmEvaluateVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadFilmEvaluateServiceImpl implements ReadFilmEvaluateService {

    @Resource
    private ReadFilmEvaluateMapper readFilmEvaluateMapper;

    @Resource
    private ReadUserMapper readUserMapper;
    @Override
    public List<FilmEvaluateVo> findAllByFilmId(Long fid) {
        List<FilmEvaluateVo> result = new ArrayList<>();
        List<FilmEvaluate> filmEvaluates = readFilmEvaluateMapper.selectListByFid(fid);
        for (FilmEvaluate filmEvaluate : filmEvaluates) {
            FilmEvaluateVo filmEvaluateVo = new FilmEvaluateVo(filmEvaluate.getId(),filmEvaluate,readUserMapper.selectById(filmEvaluate.getUid()));


            result.add(filmEvaluateVo);
        }
        return result;
    }
}
