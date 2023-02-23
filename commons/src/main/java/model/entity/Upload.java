package model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 上传的图片
 */

public class Upload {

    private Long id;

    //文件的路径
    private String path;

    //文件的后缀名
    private String suffix;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }


    public Upload(Long id, String path, String suffix) {
        this.id = id;
        this.path = path;
        this.suffix = suffix;
    }

    public String getFileName(){
        return id+"."+suffix;
    }

    @Override
    public String toString() {
        return "Upload{" +
                "id:" + id +
                ", path:'" + path + '\'' +
                ", suffix:'" + suffix + '\'' +
                '}';
    }
}
