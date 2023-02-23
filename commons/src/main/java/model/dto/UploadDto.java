package model.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件封装数据
 */
public class UploadDto {
    //文件id
    private Long id;


    //文件路径
    private String path;

    //文件格式
    private String suffix;

    //文件名
    private String fileName="";

    //文件本体
    private MultipartFile multipartFile;


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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public UploadDto(Long id, String path, String suffix, MultipartFile multipartFile) {
        this.id = id;
        this.path = path;
        this.suffix = suffix;
        this.multipartFile = multipartFile;
        this.fileName=id+"."+suffix;
    }
}
