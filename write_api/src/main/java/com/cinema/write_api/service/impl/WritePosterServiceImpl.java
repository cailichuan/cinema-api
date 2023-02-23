package com.cinema.write_api.service.impl;

import com.alibaba.excel.util.FileUtils;
import com.cinema.write_api.mapper.WritePosterMapper;
import com.cinema.write_api.mapper.WriteRoleMapper;
import com.cinema.write_api.mapper.WriteUploadMapper;
import com.cinema.write_api.service.WritePosterService;
import com.cinema.write_api.service.WriteUploadService;
import com.cinema.write_api.util.GetSnowId;
import com.cinema.write_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.Poster;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import type.RedisTable;
import type.SnowIdType;
import util.DataTimeUtil;
import util.PathUtil;
import util.RDTCUitl;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class WritePosterServiceImpl implements WritePosterService {

    @Resource
    private WritePosterMapper writePosterMapper;

    @Resource
    private WriteUploadMapper writeUploadMapper;


    @Resource
    private WriteUploadService writeUploadService;

    @Resource
    private GetSnowId getSnowId;

    @Resource
    private RedisUtils redisUtils;

    @Transactional
    @Override
    public void save(Poster poster, MultipartFile file) throws Exception {


        getSnowId.setSonwIdType(SnowIdType.POSTER);
        long id = getSnowId.nextId();

        poster.setId(id);
        poster.setCreatAt(DataTimeUtil.getNowDateTimeString());

        Long upId = writeUploadService.checkAndSaveUpload(file,"poster");
        poster.setUpid(upId);

        writePosterMapper.insert(poster);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.POSTER,String.valueOf(poster.getId()),String.valueOf(poster));

    }



    @Transactional
    @Override
    public void update(Poster poster,MultipartFile file) throws Exception {



        //重新存储图片
        Long upId = writeUploadService.checkAndSaveUpload(file,"poster");

        //删除图片
        Map<String,Long> map = new HashMap<>();
        map.put("id",poster.getId());
        List<Poster> posters = writePosterMapper.selectByMap(map);
        if (posters.size()==0) throw new Exception("不存在此海报");
        Poster poster1= posters.get(0);
        writeUploadService.deleteById(poster1.getUpid());

        poster.setUpid(upId);


        writePosterMapper.update(poster);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.POSTER,String.valueOf(poster.getId()),String.valueOf(poster));




    }

    @Transactional
    @Override
    public void deleteById(Long id) throws Exception {

        //获取图片id
        Map<String,Long> map = new HashMap<>();
        map.put("id",id);
        List<Poster> posters = writePosterMapper.selectByMap(map);
        if (posters.size() == 0) throw new Exception("不存在此海报");
        Poster poster = posters.get(0);


        //删除图片
        writeUploadService.deleteById(poster.getUpid());

        writePosterMapper.deleteByMap(map);
        log.info("删除缓存");
        redisUtils.hDelete(RedisTable.HASH.POSTER,String.valueOf(id));
    }


    @Transactional
    @Override
    public void deleteAll() throws Exception {

        Map<String,String> map = new HashMap<>();
        map.put("path","poster");

        writeUploadMapper.deleteByMap(map);


        writePosterMapper.deleteByMap(null);

        log.info("删除缓存");
        List<Object> POs = redisUtils.hValues(RedisTable.HASH.POSTER);
        for (Object po : POs) {
            Poster poter = new RDTCUitl<Poster>().getData(po, RDTCUitl.ClassName.POSTER);
            Long upid = poter.getUpid();
            redisUtils.hDelete(RedisTable.HASH.UPLOAD,String.valueOf(upid));
        }
        redisUtils.hDelete(RedisTable.HASH.POSTER);


        //删除整个海报路径
        System.out.println(PathUtil.getCommonsImgPath() + "\\poster");
        File file = new File(PathUtil.getCommonsImgPath() + "\\poster");
        if (!file.exists() ){
            throw new Exception("路径不存在");
        }

        //删除该文件夹下面所有文件
        for (File img: Objects.requireNonNull(file.listFiles())) {
            if (!img.isDirectory()) {
                img.delete();
            }
        }

        //删除改文件（文件夹下面有文件是删除不了文件夹的）
        if (!file.delete()) {
            throw new Exception("删除失败");
        }

    }
}
