package com.cinema.write_api.service.impl;

import com.cinema.write_api.mapper.WriteUploadMapper;
import com.cinema.write_api.service.WriteUploadService;
import com.cinema.write_api.util.GetSnowId;
import com.cinema.write_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.dto.UploadDto;
import model.entity.Upload;
import model.entity.User;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import type.Path;
import type.RedisTable;
import type.SnowIdType;
import util.PathUtil;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class WriteUploadServiceImpl implements WriteUploadService {

    @Resource
    private WriteUploadMapper writeUploadMapper;

    @Resource
    private GetSnowId getSnowId;

    @Resource
    private RedisUtils redisUtils;


    @Transactional
    @Override
    public Long checkAndSaveUpload(MultipartFile file,String path) throws Exception {

        //把数据存入数据库
        long id = getSnowId.setSonwIdType(SnowIdType.UPLOAD).nextId();
        String suffix = PathUtil.getSuffix(file.getOriginalFilename());
        Upload upload = new Upload(id, path, suffix);
        sava(upload);

        //把图片数据存入服务器
        UploadDto uploadDto = new UploadDto(id, path, suffix, file);
        try {
            savaUploadInFile(uploadDto);
            System.out.println("行不行啊你"+id);
            return id;
        } catch (Exception e) {

            throw new Exception(e.getMessage());
        }




    }


    //把图片数据存入数据库

    @Override
    public Upload sava(Upload upload) {
        writeUploadMapper.insert(upload);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.UPLOAD,String.valueOf(upload.getId()),String.valueOf(upload));
        return upload;
    }

    //把图片存入服务器中
    @Override
    public void savaUploadInFile(UploadDto dto) throws Exception {

        String imgPath = "";
        try {
            //生成图片路径
            imgPath = PathUtil.getCommonsImgPath()+"\\"+dto.getPath();

        } catch (FileNotFoundException e) {

            throw new Exception("路径获取失败");
        }


        //保存图片的文件夹
        File dir = new File(imgPath);
        //不存在则创建
        if (!dir.exists()){
            dir.mkdirs();
        }

        //创建文件对象
        File dest = new File(dir, dto.getFileName());

        //执行保存头像文件
        try {
            dto.getMultipartFile().transferTo(dest);
        } catch (IOException e) {
            throw new Exception("图片保存失败");
        }
    }


    @Transactional
    @Override
    public void deleteById(Long id) throws Exception {

        Map<String,Long> map = new HashMap<>();
        map.put("id",id);

        //获取图片信息
        List<Upload> uploads = writeUploadMapper.selectByMap(map);
        if (uploads.size()==0)
            throw new Exception("图片不存在");
        Upload upload = uploads.get(0);

        //生成图片路径
        String imgPath = null;
        try {
            imgPath = PathUtil.getCommonsImgPath() + "\\" + upload.getPath();

        }catch (Exception e){
            throw new Exception("图片路径生成失败");
        }
        File dir = new File(imgPath);
        if (!dir.exists()) {
            throw new Exception("路径不存在");
        }


        //生成文件名
        String fileName=upload.getFileName();


        //创建文件对象
        File file = new File(dir, fileName);
        if (!file.delete()) {
            throw new Exception("图片删除失败");
        }

        //删除数据库数据
        writeUploadMapper.deleteByMap(map);

        log.info("删除缓存");
        redisUtils.hPut(RedisTable.HASH.UPLOAD,String.valueOf(upload.getId()),String.valueOf(upload));

    }



}
