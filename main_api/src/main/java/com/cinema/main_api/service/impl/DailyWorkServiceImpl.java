package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import api.write.WriteApi;
import com.cinema.main_api.service.DailyWorkService;
import com.cinema.main_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.DailyWork;
import org.springframework.stereotype.Service;
import type.RedisTable;
import util.RDTCUitl;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class DailyWorkServiceImpl implements DailyWorkService {

    @Resource
    private ReadApi readApi;

    @Resource
    private WriteApi writeApi;

    @Resource
    private RedisUtils redisUtils;



    @Override
    public void save(DailyWork dailyWork,String uuid) throws Exception {

        writeApi.saveDaily(dailyWork,uuid);
    }

    @Override
    public List<DailyWork> findAll() throws ClassNotFoundException {
        if (redisUtils.hSize(RedisTable.HASH.DAILY_WORK) == 0) {
            log.info("查询数据库");
            List<DailyWork> allDailyWork = readApi.findAllDailyWork();

            log.info("写入缓存");
            for (DailyWork dailyWork : allDailyWork) {
                redisUtils.hPut(RedisTable.HASH.DAILY_WORK,String.valueOf(dailyWork.getId()),String.valueOf(dailyWork));
            }

            return allDailyWork;
        }

        log.info("读取缓存");
        List<DailyWork> dailyWorks = new ArrayList<>();
        List<Object> objects = redisUtils.hValues(RedisTable.HASH.DAILY_WORK);
        for (Object object : objects) {
            DailyWork dailyWork = new RDTCUitl<DailyWork>().getData(object, RDTCUitl.ClassName.DAILYWORK);
            dailyWorks.add(dailyWork);
        }

        return dailyWorks;
    }

    @Override
    public void deleteById(Long id,String uuid) throws Exception {

        writeApi.deleteDailyById(id,uuid);
    }
}
