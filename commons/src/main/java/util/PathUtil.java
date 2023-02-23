package util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public final class PathUtil {




    //获取图片的文件夹路径

    public static String getCommonsImgPath() throws FileNotFoundException {

        String path = ResourceUtils.getURL("classpath:").getPath();

        File parentFile = new File(path).getParentFile().getParentFile().getParentFile();

        return parentFile+"\\commons\\target\\classes\\static\\img";

    }


    //获取图片的格式
    public static String getSuffix(String originalFilename){
        String[] split = originalFilename.split("\\.");
        if (split.length>1) {
           return split[split.length-1];
        }

        return null;
    }
}
