package model.dto;

import model.entity.Poster;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 *海报信息的输入模型
 */
public class PosterDto {
    private Long id;

    private String title;

    private Long upid;

    //上架 下架
    private Boolean status;

    private String createAt;


    private String uuid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUpid() {
        return upid;
    }

    public void setUpid(Long upid) {
        this.upid = upid;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    @Override
    public String toString() {
        return "PosterDto{" +
                "id:" + id +
                ", title:'" + title + '\'' +
                ", upid:" + upid +
                ", status:" + status +
                ", createAt:'" + createAt + '\'' +
                ", uuid:'" + uuid + '\'' +
                '}';
    }

    public Poster toPoster(){


        return new Poster(id,title,upid,status,createAt);
    }
}
