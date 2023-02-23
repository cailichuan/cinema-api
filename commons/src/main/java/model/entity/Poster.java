package model.entity;

import lombok.Data;

/**
 * 首页海报
 */

public class Poster {

    private Long id;

    private String title;

    private Long upid;

    //上架 下架
    private Boolean status;

    private String createAt;

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

    public String getCreatAt() {
        return createAt;
    }

    public void setCreatAt(String creatAt) {
        this.createAt = creatAt;
    }


    public Poster(Long id, String title, Long upid, Boolean status, String createAt) {
        this.id = id;
        this.title = title;
        this.upid = upid;
        this.status = status;
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Poster{" +
                "id:" + id +
                ", title:'" + title + '\'' +
                ", upid:" + upid +
                ", status:" + status +
                ", creatAt:'" + createAt + '\'' +
                '}';
    }
}
