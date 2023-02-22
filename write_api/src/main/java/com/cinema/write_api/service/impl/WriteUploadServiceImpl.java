package com.cinema.write_api.service.impl;

import com.cinema.write_api.mapper.WriteUploadMapper;
import com.cinema.write_api.service.WriteUploadService;
import com.cinema.write_api.util.GetSnowId;
import model.entity.Upload;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import type.Path;
import type.SnowIdType;
import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;


@Service
public class WriteUploadServiceImpl implements WriteUploadService {

    @Resource
    private WriteUploadMapper writeUploadMapper;

    @Resource
    private GetSnowId getSnowId;
    @Override
    public Long checkAndSaveUpload(MultipartFile file,String path) {


        try {
           //获取jar包所在的目录
            ApplicationHome homePath = new ApplicationHome(getClass());
            File jarF = homePath.getSource();

            //jar包目录下生成一个upload文件夹用来存储上传的图片
            String parent = jarF.getParentFile().toString() + Path.IMAGES_PATH+"/"+path;

            //保存图片的文件夹
            File dir = new File(parent);
            //不存在则创建
            if (!dir.exists()) {
                dir.mkdirs();
            }

            //图片文件的文件后缀名
            String suffix="";
            //获取上传时的文件名
            String originalFilename = file.getOriginalFilename();
            //获取格式后缀

            String[] split = originalFilename.split("\\.");
            for (String s : split) {
                System.out.println(s);
            }
            if (split.length>1) {
                suffix = split[split.length-1];
            }

            //获取新的文件名
            Long id = getSnowId.setSonwIdType(SnowIdType.UPLOAD).nextId();
            String filename = id + "."+suffix;

            //创建文件对象
            File dest = new File(dir,filename);

            //执行保存头像文件
            file.transferTo(dest);

            //保存图片的id、路径名、文件格式进数据库
            Upload upload = new Upload(id, path, suffix);
            writeUploadMapper.insert(upload);

            //返回图片的id
            return id;

        }catch (IOException e){

            e.printStackTrace();

        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
